package com.epam.lesson15;

import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
    Vector<Integer> vector = new Vector<>();

    for (int i = 0; i < 10; i++) {
      vector.add(i);
    }

    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(new Task1(vector));
    executorService.execute(new Task2(vector));

    executorService.shutdown();
  }
}

class Task1 implements Runnable {

  private final Vector vector;

  Task1(Vector vector) {
    this.vector = vector;
  }

  @Override
  public void run() {
    System.out.println("Task1 vector size " + vector.size());
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Task1 vector[0]" + vector.get(0));
  }
}

class Task2 implements Runnable {

  private final Vector vector;

  Task2(Vector vector) {
    this.vector = vector;
  }

  @Override
  public void run() {
    Iterator<Integer> iterator = vector.iterator();
    while (iterator.hasNext()) {
      iterator.next();
      iterator.remove();
    }
    System.out.println("Task2 vector size " + vector.size());
  }
}