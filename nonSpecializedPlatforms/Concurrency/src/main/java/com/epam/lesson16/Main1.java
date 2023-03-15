package com.epam.lesson16;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.IntStream;

public class Main1 {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("A");
    list.add("B");
    list.add("C");
    list.add("D");

    Spliterator<String> spliterator1 = list.spliterator();
    Spliterator<String> spliterator2 = spliterator1.trySplit();
    spliterator1.forEachRemaining(System.out::println);
    spliterator2.forEachRemaining(System.out::println);

//    IntStream.range(0, 10)
//        .parallel()
//        .peek(
//            value -> System.out.println(value + " produced by " + Thread.currentThread().getName()))
//        .forEachOrdered(System.out::println);

//    long result = IntStream.range(0, 10000)
//        .parallel()
//        .peek(
//            value -> System.out.println(value + " produced by " + Thread.currentThread().getName()))
//        .filter(Main1::isPrime)
//        .count();
//    System.out.println("Result: " + result);
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