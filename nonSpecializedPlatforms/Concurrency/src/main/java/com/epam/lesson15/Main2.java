package com.epam.lesson15;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main2 {

  public static void main(String[] args) {
    CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }

    ExecutorService executorService = Executors.newCachedThreadPool();
    executorService.execute(new Task3(list));
    executorService.execute(new Task4(list));

    executorService.shutdown();
  }
}

class Task3 implements Runnable {

  private final CopyOnWriteArrayList<Integer> list;

  Task3(CopyOnWriteArrayList<Integer> list) {
    this.list = list;
  }

  @Override
  public void run() {
    System.out.println("Task3 list size " + list.size());
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }
}

class Task4 implements Runnable {

  private final CopyOnWriteArrayList<Integer> list;

  Task4(CopyOnWriteArrayList<Integer> list) {
    this.list = list;
  }

  @Override
  public void run() {
    list.remove(0);
    list.remove(1);
    System.out.println("Task4 list size " + list.size());
  }
}