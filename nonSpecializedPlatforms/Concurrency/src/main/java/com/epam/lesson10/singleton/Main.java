package com.epam.lesson10.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(() -> Printer.getInstance().print());
    executorService.execute(() -> Printer.getInstance().print());
//    executorService.execute(Printer.Holder.INSTANCE::print);
//    executorService.execute(Printer.Holder.INSTANCE::print);
    executorService.shutdown();
  }
}

class Printer {

  private static volatile Printer instance;

  private Printer() {
    System.out.println("Creating printer");
  }

//  public static class Holder {
//
//    public static final Printer INSTANCE = new Printer();
//  }

  public void print() {
    System.out.println("Print");
  }

  public static String getVersion() {
    return "1.0";
  }

  public static Printer getInstance() {
    if (instance == null) {
      synchronized (Printer.class) {
        if (instance == null) {
          instance = new Printer();
        }
      }
    }
    return instance;
  }
}