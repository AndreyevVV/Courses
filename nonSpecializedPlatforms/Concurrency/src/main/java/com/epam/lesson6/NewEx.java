package com.epam.lesson6;

import com.epam.lesson6.Main3.Task1;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class NewEx {

  static class Task1 implements Runnable {

    @Override
    public void run() {
      int x = 1 / 0;
    }
  }

  public static void main(String[] args) {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
    executor.setThreadFactory(new ThreadFactory() {
      @Override
      public Thread newThread(Runnable r) {
        return null;
      }
    });
//    ExecutorService executorService = Executors.newFixedThreadPool(1);
////    executorService.execute(new Task1());
//    Future future = executorService.submit(new Task1());
//    try {
//      future.get();
//    } catch (Exception e) {
//      System.out.println("Exception: " + e);
//    }
//    executorService.shutdown();
  }
}