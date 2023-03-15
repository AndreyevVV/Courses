package com.epam.lesson4;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsTask {

  static class MyTask implements Runnable {

    private final String id;

    public MyTask(String id) {
      this.id = id;
    }

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        System.out.println("Running task " + id + " step " + i);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
//    Executor executor = Executors.newSingleThreadExecutor();
//    Executor executor = Executors.newCachedThreadPool();
//    Executor executor = Executors.newFixedThreadPool(2);
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    executorService.execute(new MyTask("Task one"));
    executorService.shutdown();

//    executor.execute(new MyTask("Task one"));
//    executor.execute(new MyTask("Task two"));
  }
}