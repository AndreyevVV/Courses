package com.epam.lesson12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Task implements Runnable {

  private final Object lock1;
  private final Object lock2;

  Task(Object lock1, Object lock2) {
    this.lock1 = lock1;
    this.lock2 = lock2;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      System.out.println(Thread.currentThread().getName() + " acquiring lock " + lock1);
      synchronized (lock1) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " acquiring lock " + lock2);
        synchronized (lock2) {
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}

public class Deadlock {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    Object lock1 = new Object();
    Object lock2 = new Object();

    executorService.execute(new Task(lock1, lock2));
    executorService.execute(new Task(lock2, lock1));

    executorService.shutdown();
  }
}