package com.epam.lesson5;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class MainEx {

  static class MyTask implements Callable<String> {

    @Override
    public String call() throws Exception {
      System.out.println("Task began");
      Thread.sleep(3000);
      System.out.println("Task returns result");
      throw new RuntimeException("My error");
//      return "task result";
    }
  }

  static class YourTask implements Runnable {

    @Override
    public void run() {
      System.out.println("Task began");
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Future<String> future = executorService.submit(new MyTask());
    System.out.println("Waiting for result");
    String result = null;
    try {
      result = future.get();
    } catch (ExecutionException e) {
      System.out.println("Exception in my task!");
      System.out.println(e.getCause().getMessage());
    }
    System.out.println("Result: " + result);

    Future<?> futureRun = executorService.submit(new YourTask());
//    Future<?> futureRun = executorService.submit(new YourTask(), true);

    System.out.println(futureRun.get());

    executorService.shutdown();
    executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
  }
}