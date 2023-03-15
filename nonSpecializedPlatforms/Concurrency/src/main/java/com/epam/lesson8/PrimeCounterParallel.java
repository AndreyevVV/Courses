package com.epam.lesson8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PrimeCounterParallel implements PrimeCounter {

  private final AtomicInteger count = new AtomicInteger();

  class CountingTask implements Runnable {

    private final int from;
    private final int to;

    CountingTask(int from, int to) {
      this.from = from;
      this.to = to;
    }

    @Override
    public void run() {
      for (int i = from; i <= to; i++) {
        if (isPrime(i)) {
          count.getAndIncrement();
        }
      }
    }

    private boolean isPrime(int value) {
      if (value == 1) {
        return false;
      }
      for (int i = 2; i < value; i++) {
        if (value % i == 0) {
          return false;
        }
      }
      return true;
    }
  }

  @Override
  public void countPrimes(int start, int end) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(new CountingTask(1, 4999));
    executorService.execute(new CountingTask(5000, 10000));
    executorService.shutdown();
    try {
      executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public int getResult() {
    return count.get();
  }
}