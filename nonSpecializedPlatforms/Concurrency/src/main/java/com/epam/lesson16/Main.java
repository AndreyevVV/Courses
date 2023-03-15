package com.epam.lesson16;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {

  public static void main(String[] args) {
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    long startTime = System.nanoTime();
    int result = forkJoinPool.invoke(new CountPrimes(0, 10000));
    float deltaTime = (System.nanoTime() - startTime) * 1e-9f;
    System.out.println("deltaTime: " + deltaTime);
    System.out.println("Result: " + result);
  }
}

class CountPrimes extends RecursiveTask<Integer> {

  private final int from;
  private final int to;

  CountPrimes(int from, int to) {
    this.from = from;
    this.to = to;
  }

  @Override
  protected Integer compute() {
    if (to - from < 1000) {
      return countPrimes(from, to);
    } else {
      CountPrimes countPrimes1 = new CountPrimes(from, (from + to) / 2 - 1);
      CountPrimes countPrimes2 = new CountPrimes((from + to) / 2, to);
      countPrimes1.fork();
      countPrimes2.fork();

      return countPrimes1.join() + countPrimes2.join();
    }
  }

  private Integer countPrimes(int from, int to) {
    int count = 0;
    for (int i = from; i <= to; i++) {
      if (isPrime(i)) {
        count++;
      }
    }
    return count;
  }

  private boolean isPrime(int value) {
    if (value < 2) {
      return false;
    }
    if (value == 2) {
      return true;
    }
    for (int i = 2; i <= value / 2; i++) {
      if (value % i == 0) {
        return false;
      }
    }
    return true;
  }
}