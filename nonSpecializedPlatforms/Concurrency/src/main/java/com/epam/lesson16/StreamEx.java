package com.epam.lesson16;

import java.util.stream.IntStream;

public class StreamEx {

  public static void main(String[] args) {
    int res = IntStream.range(0, 100).parallel().reduce(100, (x, y) -> x + y);
    System.out.println(res);

    System.out.println("--------");

    int result = IntStream.rangeClosed(1, 1000)
        .filter(i -> i > 50).findFirst().getAsInt();
    System.out.println(result);

    System.out.println("--------");
  }
}