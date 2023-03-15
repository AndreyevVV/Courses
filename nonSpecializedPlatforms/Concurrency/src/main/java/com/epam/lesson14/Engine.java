package com.epam.lesson14;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Engine {

  interface Callback {

    void onSuccess();
  }

  void init(Callback callback) {
    ExecutorService service = Executors.newSingleThreadExecutor();
    System.out.println("Initializing");
    service.submit(() -> {
      delay(2000);
      callback.onSuccess();
    });
  }

  private void delay(int i) {
    try {
      Thread.sleep(i);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    Engine engine = new Engine();
    engine.init(new Callback() {
      @Override
      public void onSuccess() {
        System.out.println("Successfully initialized");
        latch.countDown();
      }
    });
    boolean result = latch.await(4, TimeUnit.SECONDS);
    System.out.println(result ? "Test passed" : "Test failed");
  }
}