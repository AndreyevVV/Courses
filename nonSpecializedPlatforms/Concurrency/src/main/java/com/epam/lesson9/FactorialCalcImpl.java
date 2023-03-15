package com.epam.lesson9;

public class FactorialCalcImpl implements FactorialCalc {

  @Override
  public long calculateFactorial(int arg) {
    long result = 1;
    for (int i = 1; i <= arg; i++) {
      result *= i;
    }
    return result;
  }
}