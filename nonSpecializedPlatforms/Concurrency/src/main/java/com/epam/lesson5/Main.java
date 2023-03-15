package com.epam.lesson5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

  static class MyTask implements Runnable {

    private final String name;

    MyTask(final String name) {
      this.name = name;
    }

    @Override
    public void run() {
      System.out.println(
          "Task " + name + " started in thread " + Thread.currentThread().getName() + Thread
              .currentThread().getId());
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        System.err.println("Thread interrupted");
      }
      System.out.println("Done");
    }
  }

  public static void main(String[] args) throws InterruptedException {
//    ExecutorService executorService = Executors.newSingleThreadExecutor();
//    ExecutorService executorService = Executors.newFixedThreadPool(3);
//    ExecutorService executorService = Executors
//        .newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(new MyTask("Task 1"));
    executorService.execute(new MyTask("Task 2"));
    executorService.execute(new MyTask("Task 3"));
    executorService.shutdownNow();
    executorService.shutdown();
    executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
    if (!executorService.isTerminated()) {
      executorService.shutdownNow();
    }
    System.out.println("All tasks completed: " + executorService.isTerminated());
    System.out.println("Main done");
  }
}