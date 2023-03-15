package com.epam.lesson5;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleEx {

  static class MyTask implements Runnable {

    private final String name;
    private static int ndx;

    MyTask(final String name) {
      this.name = name;
      ndx++;
    }

    @Override
    public void run() {
      System.out.println(
          "Task " + ndx + " started in thread " + Thread.currentThread().getName() + Thread
              .currentThread().getId());
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        System.err.println("Thread interrupted");
      }
      System.out.println("Done");
    }
  }

  public static void main(String[] args) throws InterruptedException {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    executorService.scheduleAtFixedRate(new MyTask("Task scheduled"),
        0, 1, TimeUnit.SECONDS);
  }
}