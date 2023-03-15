package com.epam.lesson7;

import com.google.common.util.concurrent.MoreExecutors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

  public static void main(String[] args) {
    ThreadPoolExecutor es =
        (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    ExecutorService service = MoreExecutors.getExitingExecutorService(es, 10, TimeUnit.SECONDS);
//    es.setThreadFactory(new ThreadFactory() {
//      @Override
//      public Thread newThread(Runnable r) {
//        Thread thread = Executors.defaultThreadFactory().newThread(r);
//        thread.setDaemon(true);
//        return thread;
//      }
//    });
    es.submit(() -> System.out.println("Task is done"));
  }
}