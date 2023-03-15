package com.epam.lesson3;

class SleepingBeauty implements Runnable {

  private Thread t;

  @Override
  public void run() {
    System.out.println("Sleeping...");
    while (true) {
      try {
        Thread.sleep(Integer.MAX_VALUE);
      } catch (InterruptedException e) {
        break;
      }
    }
    System.out.println("OK.");
  }

  public void sleep() {
    t = new Thread(this);
    t.start();
  }

  public void awake() {
    t.interrupt();
  }
}

class Main5 {

  public static void main(String[] args) throws InterruptedException {
    SleepingBeauty sleepingBeauty = new SleepingBeauty();
    sleepingBeauty.sleep();
    Thread.sleep(2000);
    sleepingBeauty.awake();
  }
}