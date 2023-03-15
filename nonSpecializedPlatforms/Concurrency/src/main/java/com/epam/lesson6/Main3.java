package com.epam.lesson6;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//example deadlock
public class Main3 {

  static class Task1 implements Runnable {

    @Override
    public void run() {
      System.out.println("Started task1");
      Future result = executorService.submit(new Task2());
      try {
        result.get();
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("Completed task1");
    }
  }

  static class Task2 implements Runnable {

    @Override
    public void run() {
      System.out.println("Started task2");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Completed task2");
    }
  }

  //  static ExecutorService executorService = Executors.newCachedThreadPool();
  static ExecutorService executorService = Executors.newSingleThreadExecutor();

  public static void main(String[] args) throws InterruptedException {
    executorService.submit(new Task1());
    Thread.sleep(1000);
    executorService.shutdown();
  }
}