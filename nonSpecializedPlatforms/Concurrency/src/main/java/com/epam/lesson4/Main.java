package com.epam.lesson4;

public class Main {

  static class Spaceship implements Runnable {

    @Override
    public void run() {
      boolean result = false;
      try {
        for (int i = 0; i < 10; i++) {
          prepareLaunch(i);
        }
        result = true;
      } catch (InterruptedException e) {
        log("Launch interrupted");
      }
      log(result ? "Launched" : "Launch cancelled");
    }

    private void prepareLaunch(int stage) throws InterruptedException {
      log("Preparing to launch stage: " + stage);
      for (int i = 0; i < 100000; i++) {
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        checkSystems();
      }
    }

    private void checkSystems() throws InterruptedException {
      Thread.sleep(1000);
    }

    private void log(String message) {
      System.out.println(message);
    }
  }

  public static void main(String[] args) throws InterruptedException {
//    System.out.println("Hello main thread " + Thread.currentThread().getName());
//    new Thread(() ->
//        System.out.println("Hello concurrent thread " + Thread.currentThread().getName())
//    ).start();
    Thread t = new Thread(new Spaceship());
    t.start();
    Thread.sleep(1000);
    t.interrupt();
  }
}