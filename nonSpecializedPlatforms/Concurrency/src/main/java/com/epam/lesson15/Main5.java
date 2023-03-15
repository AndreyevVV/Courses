package com.epam.lesson15;

import java.util.concurrent.ForkJoinPool;

public class Main5 {

  public static void main(String[] args) {
    ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(600);
    System.out.println(forkJoinPool.invoke(simpleRecursiveAction));
  }
}