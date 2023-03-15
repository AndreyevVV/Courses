package com.epam.lesson12;

import java.util.concurrent.BlockingQueue;

public class ConsumerEx implements Runnable {

  private final BlockingQueue<String> queue;

  public ConsumerEx(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      System.out.println("Got: " + queue.take() + " queue size " + queue.size());
      System.out.println("Got: " + queue.take() + " queue size " + queue.size());
      System.out.println("Got: " + queue.take() + " queue size " + queue.size());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}