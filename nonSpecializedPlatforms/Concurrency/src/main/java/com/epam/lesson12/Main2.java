package com.epam.lesson12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Task1 implements Runnable {

  private final Lock lock;

  Task1(Lock lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    try {
      lock.lock();
      System.out.println("Task 1 is running");
      try {
        Thread.sleep(60000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } finally {
      lock.unlock();
    }
  }
}

class Task2 implements Runnable {

  private final Lock lock;

  Task2(Lock lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    boolean result = false;
    try {
      try {
        result = lock.tryLock(1, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Task 2 is running: " + result);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } finally {
      if (result) {
        lock.unlock();
      }
    }
  }
}

public class Main2 {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

    Lock lock = new ReentrantLock();

    executorService.execute(new Task1(lock));
    executorService.execute(new Task2(lock));

    executorService.shutdown();
  }
}