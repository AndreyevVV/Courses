package com.epam.lesson7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Festival {

  private final ExecutorCompletionService<BandResult> exService;
  private final ThreadPoolExecutor service;
  private int stageNum = 1;

  public Festival(int stageCount) {
    service = (ThreadPoolExecutor) Executors.newFixedThreadPool(stageCount);
    service.setThreadFactory(new ThreadFactory() {
      @Override
      public Thread newThread(Runnable r) {
        Thread thread = Executors.defaultThreadFactory().newThread(r);
        thread.setName("Сцена " + stageNum);
        stageNum++;
        return thread;
      }
    });

//    service.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
    service.setRejectedExecutionHandler(new RejectedExecutionHandler() {
      @Override
      public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Группа не смогла выступить, выступает вне сцены");
        r.run();
      }
    });

    exService = new ExecutorCompletionService<BandResult>(service);
  }

  public Future<BandResult> accept(Band band) {
    return exService.submit(band);
  }

  void close() throws InterruptedException {
    service.shutdown();
    service.awaitTermination(10, TimeUnit.SECONDS);
    if (service.isTerminated()) {
      service.shutdownNow();
    }
  }

  public BandResult getResult() throws InterruptedException, ExecutionException {
    return exService.take().get();
  }
}