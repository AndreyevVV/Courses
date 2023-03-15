package com.epam.lesson9;

public class FactorialCached implements FactorialCalc {

  int cachedArg;
  long cachedResult;

  @Override
  public synchronized long calculateFactorial(int arg) {
    if (cachedArg == arg) {
      return cachedResult;
    } else {
      cachedArg = arg;
      cachedResult = calc(arg);
      return cachedResult;
    }
  }

  private long calc(int arg) {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    long result = 1;
    for (int i = 1; i <= arg; i++) {
      result *= i;
    }
    return result;
  }
}