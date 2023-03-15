package com.epam.lesson14;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierEx {

  public static void main(String[] args) throws InterruptedException {
    CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
      @Override
      public void run() {
        System.out.println("Play begins");
      }
    });

    Musicant musicant1 = new Musicant("piano", barrier);
    Musicant musicant2 = new Musicant("violin", barrier);
    Musicant musicant3 = new Musicant("drums", barrier);
    Musicant musicant4 = new Musicant("guitar", barrier);

    ExecutorService service = Executors.newFixedThreadPool(4);
    service.submit(musicant1);
    service.submit(musicant2);
    service.submit(musicant3);
    service.submit(musicant4);

    service.shutdown();
  }
}

class Musicant implements Runnable {

  private final String instrument;
  private static final Random random = new Random();
  private final CyclicBarrier barrier;

  Musicant(String instrument, CyclicBarrier barrier) {
    this.instrument = instrument;
    this.barrier = barrier;
  }

  @Override
  public void run() {
    tuneIn();
    try {
      barrier.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (BrokenBarrierException e) {
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
  }

  private void play() {
    log(instrument + " playing");
  }

  private void log(String s) {
    System.out.println(s);
  }
}