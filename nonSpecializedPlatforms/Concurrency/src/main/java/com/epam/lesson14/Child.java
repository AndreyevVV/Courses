package com.epam.lesson14;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

enum Toy {
  BEAR, DOLL
}

public class Child implements Runnable {

  private final String name;
  private final Exchanger<Toy> exchanger;
  private final int delay;
  private Toy toy;

  Child(String name, Exchanger<Toy> exchanger, int delay, Toy toy) {
    this.name = name;
    this.exchanger = exchanger;
    this.delay = delay;
    this.toy = toy;
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      System.out.println(name + " plays with " + toy);
      try {
        Thread.sleep(delay * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      try {
        System.out.println(name + " done playing");
        toy = exchanger.exchange(toy);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public String toString() {
    return "Child{" +
        "name='" + name + '\'' +
        ", toy=" + toy +
        '}';
  }

  public static void main(String[] args) throws InterruptedException {
    ExecutorService service = Executors.newFixedThreadPool(2);
    Exchanger<Toy> exchanger = new Exchanger<>();
    service.submit(new Child("Dima", exchanger, 5, Toy.BEAR));
    service.submit(new Child("Lena", exchanger, 1, Toy.DOLL));

    Thread.sleep(10000);
    service.shutdownNow();
  }
}