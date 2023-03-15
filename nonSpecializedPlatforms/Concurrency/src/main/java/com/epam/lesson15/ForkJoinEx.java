package com.epam.lesson15;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinEx {

  public static void main(String[] args) {
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    forkJoinPool.invoke(new MyRecursiveTask(40));
  }
}