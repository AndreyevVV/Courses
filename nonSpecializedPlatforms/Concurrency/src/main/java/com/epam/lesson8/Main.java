package com.epam.lesson8;

public class Main {

  public static void main(String[] args) {
//    PrimeCounter counter = new PrimeCounterSerial();
    PrimeCounter counter = new PrimeCounterParallel();
    counter.countPrimes(1, 10000);
    int result = counter.getResult();
    System.out.println(result);
  }
}