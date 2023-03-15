package com.epam.lesson13;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchEx {

  private static CountDownLatch latch = new CountDownLatch(3);

  static class Service implements Runnable {

    private final String name;
    private final int delay;

    Service(String name, int delay) {
      this.name = name;
      this.delay = delay;
    }

    @Override
    public void run() {
      System.out.println("Initializing service: " + name);
      try {
        Thread.sleep(delay * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Service initialized: " + name);
      latch.countDown();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService service = Executors.newFixedThreadPool(3);
    Service s1 = new Service("Network", 5);
    Service s2 = new Service("Disk", 3);
    Service s3 = new Service("Fingerprints", 2);

    System.out.println("Waiting for services to initialize");
    service.execute(s1);
    service.execute(s2);
    service.execute(s3);
    latch.await();
    System.out.println("Services initialized, booting up ");
    service.shutdown();
    service.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
  }
}