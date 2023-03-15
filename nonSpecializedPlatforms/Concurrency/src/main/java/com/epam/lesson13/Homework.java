package com.epam.lesson13;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Homework {

  static class Service implements Runnable {

    private final CountDownLatch latch;
    private final int number;

    Service(CountDownLatch latch, int number) {
      this.latch = latch;
      this.number = number;
    }

    @Override
    public void run() {
      latch.countDown();
      System.out.println("Thread " + number + " is ready to start/latched");
      try {
        latch.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Thread " + number + " started");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    final int threadNumbers = 4;
    final ExecutorService executorService = Executors.newFixedThreadPool(threadNumbers);
    final CountDownLatch latch = new CountDownLatch(threadNumbers);

    for (int i = 0; i < threadNumbers; i++) {
      Service service = new Service(latch, i);
      executorService.execute(service);
    }

    executorService.shutdown();
    executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
  }
}