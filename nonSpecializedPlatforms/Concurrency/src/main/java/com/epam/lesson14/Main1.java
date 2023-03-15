package com.epam.lesson14;

import java.util.PriorityQueue;

public class Main1 {

  public static void main(String[] args) {
    PriorityQueue<Integer> queue = new PriorityQueue<>();
    for (int i = 0; i < 10; i++) {
      queue.add(i);
    }

    while (queue.size() > 0) {
      int value = queue.poll();
      System.out.println(value);
    }
  }
}