package com.epam.lesson7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class BandResult {

  private final String name;
  private final int payment;

  public BandResult(String name, int payment) {
    this.name = name;
    this.payment = payment;
  }

  @Override
  public String toString() {
    return "Группе " + name + " выплачено " + payment;
  }
}

public class MainEx {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    Festival festival = new Festival(2);

    Band band1 = new Band("Кино",
        Arrays.asList("Группа крови", "Звезда по имени солнце"), 4000);
    Band band2 = new Band("Ария",
        Arrays.asList("Я свободен", "Герой асфальта"), 1000);
    Band band3 = new Band("Сплин",
        Arrays.asList("Выхода нет", "Танцуй", "Остаемся зимовать"), 2000);
    Band band4 = new Band("Boney M",
        Arrays.asList("Sunny", "Belfast"), 1000);

    List<Future<BandResult>> futures = new ArrayList<>();
    futures.add(band1.play(festival));
    futures.add(band2.play(festival));
    futures.add(band3.play(festival));

    for (int i = 0; i < 3; i++) {
      BandResult result = festival.getResult();
      System.out.println("Получено вознаграждение " + result);
    }

    festival.close();

    band4.play(festival);
  }
}