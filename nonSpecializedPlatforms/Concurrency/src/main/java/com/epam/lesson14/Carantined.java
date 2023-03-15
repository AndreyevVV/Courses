package com.epam.lesson14;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Carantined implements Delayed {

  private final String name;
  private final Date date;

  Carantined(Date date, String name) {
    this.name = name;
    this.date = date;
  }

  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(date.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed o) {
    long diff = date.getTime() - ((Carantined) o).date.getTime();
    return (int) diff;
  }

  @Override
  public String toString() {
    return "Carantined{" +
        "name='" + name + '\'' +
        ", date=" + date +
        '}';
  }

  public static void main(String[] args) throws InterruptedException {
    Carantined carantined1 = new Carantined(new Date(System.currentTimeMillis() + 1000), "Jack");
    Carantined carantined2 = new Carantined(new Date(System.currentTimeMillis() + 200), "John");
    Carantined carantined3 = new Carantined(new Date(System.currentTimeMillis() + 2000), "Jill");

    DelayQueue<Carantined> queue = new DelayQueue<>();
    queue.put(carantined1);
    queue.put(carantined2);
    queue.put(carantined3);

    System.out.println(queue);

    for (int i = 0; i < 3; i++) {
      Carantined carantined = queue.take();
      System.out.println("Released " + carantined);
    }
  }
}