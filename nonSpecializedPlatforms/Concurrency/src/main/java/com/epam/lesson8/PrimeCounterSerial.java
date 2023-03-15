package com.epam.lesson8;

public class PrimeCounterSerial implements PrimeCounter {

  private int count = 0;

  @Override
  public void countPrimes(int start, int end) {
    for (int i = start; i < end; i++) {
      if (isPrime(i)) {
        count++;
      }
    }
  }

  @Override
  public int getResult() {
    return count;
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