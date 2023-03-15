package com.epam.lesson12;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Main3 {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    ProducerEx producer = new ProducerEx(queue);
    ConsumerEx consumer = new ConsumerEx(queue);

    executorService.execute(producer);
    executorService.execute(consumer);

    executorService.shutdown();
  }
}