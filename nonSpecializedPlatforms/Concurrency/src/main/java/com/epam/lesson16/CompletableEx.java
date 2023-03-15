package com.epam.lesson16;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableEx {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 5);
    future.thenAccept(System.out::println);

    PrimeCounter primeCounter = new PrimeCounter();
    CompletableFuture<Integer> future1 = primeCounter.countPrimes(10000);
    System.out.println(future.get());
    future = primeCounter.countPrimes(10000);
    System.out.println(future.get());
  }
}

class PrimeCounter {

  int res;

  public CompletableFuture<Integer> countPrimes(int to) {
    CompletableFuture<Integer> future = new CompletableFuture<>();
    if (res != 0) {
      System.out.println("Returning completed");
      future.complete(res);
      return future;
    }
    future = CompletableFuture.supplyAsync(() -> res = countPrimes(1, 10000));
    return future;
  }

  static int countPrimes(int from, int to) {
    int count = 0;
    for (int i = from; i <= to; i++) {
      if (isPrime(i)) {
        count++;
      }
    }
    return count;
  }

  private static boolean isPrime(int value) {
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