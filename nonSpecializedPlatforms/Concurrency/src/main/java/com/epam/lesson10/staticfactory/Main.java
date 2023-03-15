package com.epam.lesson10.staticfactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyTask implements Runnable {

  private final int delay;
  private final StopWatch stopWatch;

  MyTask(int delay, StopWatch stopWatch) {
    this.delay = delay;
    this.stopWatch = stopWatch;
  }

  @Override
  public void run() {
    stopWatch.start();
    try {
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    stopWatch.end();
  }
}

interface StopWatch {

  void start();

  void end();
}

class StopWatchImpl implements StopWatch {

  private final ThreadLocal<Long> start = new ThreadLocal<>();

  @Override
  public void start() {
    start.set(System.currentTimeMillis());
  }

  @Override
  public void end() {
    long end = System.currentTimeMillis();
    System.out.println(Thread.currentThread().getName() + " completed in " + (end - start.get()));
  }
}

class Main {

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    StopWatch stopWatch = new StopWatchImpl();
    executorService.submit(new MyTask(3000, stopWatch));
    Thread.sleep(1000);
    executorService.submit(new MyTask(3000, stopWatch));
    executorService.shutdown();
  }
}