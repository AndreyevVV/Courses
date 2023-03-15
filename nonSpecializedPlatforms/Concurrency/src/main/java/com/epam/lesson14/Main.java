package com.epam.lesson14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch readyLatch = new CountDownLatch(4);
    CountDownLatch startLatch = new CountDownLatch(1);

    Musician musician1 = new Musician("piano", readyLatch, startLatch);
    Musician musician2 = new Musician("violin", readyLatch, startLatch);
    Musician musician3 = new Musician("drums", readyLatch, startLatch);
    Musician musician4 = new Musician("guitar", readyLatch, startLatch);

    ExecutorService service = Executors.newFixedThreadPool(4);
    service.submit(musician1);
    service.submit(musician2);
    service.submit(musician3);
    service.submit(musician4);

    readyLatch.await();
    System.out.println("All musicians tuned in");
    startLatch.countDown();

    service.shutdown();
  }
}

class Musician implements Runnable {

  private final String instrument;
  private static final Random random = new Random();
  private final CountDownLatch readyLatch;
  private final CountDownLatch startLatch;

  Musician(String instrument, CountDownLatch readyLatch, CountDownLatch startLatch) {
    this.instrument = instrument;
    this.readyLatch = readyLatch;
    this.startLatch = startLatch;
  }

  @Override
  public void run() {
    tuneIn();
    try {
      startLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    play();
  }

  private void tuneIn() {
    log(instrument + " tuning");
    try {
      Thread.sleep(random.nextInt(3) + 1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log(instrument + " tuned");
    readyLatch.countDown();
  }

  private void play() {
    log(instrument + " playing");
  }

  private void log(String s) {
    System.out.println(s);
  }
}