package com.epam.lesson15;

import java.util.concurrent.RecursiveAction;

class MyRecursiveTask extends RecursiveAction {

  private final int count;

  MyRecursiveTask(int count) {
    this.count = count;
  }

  @Override
  protected void compute() {
    if (count < 10) {
      System.out.println("Serial work: " + count + " in " + Thread.currentThread().getName());
    } else {
      System.out.println("Parallel work: " + count + " in " + Thread.currentThread().getName());
      MyRecursiveTask subtask1 = new MyRecursiveTask(count / 2);
      MyRecursiveTask subtask2 = new MyRecursiveTask(count / 2);
      subtask1.fork();
      subtask2.fork();
    }
  }
}