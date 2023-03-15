package com.epam.lesson14;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

class City implements Runnable {

  private final String city;
  private final Phaser phaser;
  private int diseaseCount;
  private static final Random random = new Random();

  public City(String city, Phaser phaser, int diseaseCount) {
    this.city = city;
    this.phaser = phaser;
    this.diseaseCount = diseaseCount;
    phaser.register();
  }

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      if (diseaseCount > 0) {
        int delta = random.nextInt(diseaseCount) / 2 + 1;
        diseaseCount -= delta;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(city + ": " + diseaseCount);
      if (diseaseCount > 0) {
        phaser.arriveAndAwaitAdvance();
      } else {
        phaser.arriveAndDeregister();
        break;
      }
    }
  }

  public int getDiseaseCount() {
    return diseaseCount;
  }

  public static void main(String[] args) {
    List<City> cities = new ArrayList<>();
    ExecutorService service = Executors.newFixedThreadPool(3);
    Phaser phaser = new Phaser() {
      @Override
      protected boolean onAdvance(int phase, int registeredParties) {
        int total = 0;
        for (City city : cities) {
          total += city.getDiseaseCount();
        }
        System.out.println("Total: " + total);
        return super.onAdvance(phase, registeredParties);
      }
    };
    City city1 = new City("Amsterdam", phaser, 20);
    City city2 = new City("Tokyo", phaser, 50);
    City city3 = new City("Paris", phaser, 100);

    cities.add(city1);
    cities.add(city2);
    cities.add(city3);

    service.execute(city1);
    service.execute(city2);
    service.execute(city3);

    service.shutdown();
  }
}