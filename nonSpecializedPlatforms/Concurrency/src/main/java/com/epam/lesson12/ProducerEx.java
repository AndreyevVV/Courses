package com.epam.lesson12;

import java.util.concurrent.BlockingQueue;

public class ProducerEx implements Runnable {

  private final BlockingQueue<String> queue;

  public ProducerEx(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      System.out.println("Put element 1: " + " queue size " + queue.size());
      queue.put("element 1");
      System.out.println("Put element 2: " + " queue size " + queue.size());
      queue.put("element 2");
      System.out.println("Put element 3: " + " queue size " + queue.size());
      queue.put("element 3");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}