package com.epam.lesson4;

import java.lang.Thread.UncaughtExceptionHandler;

public class MyTask implements Runnable {

  private final boolean safeMode;

  MyTask(boolean safeMode) {
    this.safeMode = safeMode;
  }

  @Override
  public void run() {
    if (!safeMode) {
      throw new RuntimeException("My exception!");
    } else {
      System.out.println("Running in safeMode");
    }
  }

  static class MyTask2 implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < 10; i++) {
        System.out.println("i=" + i);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  static Thread.UncaughtExceptionHandler handler = new UncaughtExceptionHandler() {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
      System.err.println("Error! Run in safe mode!");
      Thread thread = new Thread(new MyTask(true));
      thread.start();
    }
  };

  public static void main(String[] args) throws InterruptedException {
    Thread.setDefaultUncaughtExceptionHandler(handler);
//    Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//      @Override
//      public void uncaughtException(Thread t, Throwable e) {
//        System.err.println("CAUGHT EXCEPTION");
//      }
//    });
    Thread t = new Thread(new MyTask(false));
//    t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//      @Override
//      public void uncaughtException(Thread t, Throwable e) {
//        System.err.println("CAUGHT EXCEPTION");
//      }
//    });
    t.start();
//    Thread t2 = new Thread(new MyTask2());
//    t2.start();
    t.join();
//    t2.join();
    System.out.println("Done");
  }
}