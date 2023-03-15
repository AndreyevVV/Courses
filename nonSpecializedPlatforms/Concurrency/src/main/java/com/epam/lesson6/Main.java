package com.epam.lesson6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {

  static class MyTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
      System.out.println("Task started");
      for (int i = 0; i < 100000; i++) {
        if (Thread.interrupted()) {
          System.out.println("Interrupted!");
          break;
        }
      }
//      Thread.sleep(5000);
      System.out.println("Task completed");
      return 42;
    }
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    MyTask myTask = new MyTask();

    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Future<Integer> future = executorService.submit(myTask);
    Thread.sleep(300);
    boolean cancelled = future.cancel(true);

    System.out.println("cancelled=" + cancelled);

//    int result = future.get();
//    System.out.println("result " + result);

//    executorService.shutdown();
//    executorService.awaitTermination(10, TimeUnit.SECONDS);
  }
}