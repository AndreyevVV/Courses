package com.epam.lesson12;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockEx {

  private static final BlockingQueue<Integer> que = new ArrayBlockingQueue<>(500);
  private static volatile boolean stopped = false;

  public static void main(String[] args) throws InterruptedException {
    Thread t = new Thread(() -> {
      try {
        for (int i = 0; i < 1000; i++) {
          if (stopped) {
            throw new InterruptedException();
          }
          System.out.println("Putting " + i + " into the que");
          que.put(i);
        }
      } catch (InterruptedException x) {
        System.out.println("Interrupted");
      }
    });

    t.start();
    Thread.sleep(1000);
    System.out.println("Requesting thread to interrupt");
    stopped = true;
  }
}