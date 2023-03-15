package com.epam.lesson9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

  static class ClientTask implements Runnable {

    private final FactorialCalc factorialCalc;
    private final String name;

    ClientTask(String name, FactorialCalc factorialCalc) {
      this.name = name;
      this.factorialCalc = factorialCalc;
    }

    @Override
    public void run() {
      long result = factorialCalc.calculateFactorial(10);
      System.out.println(name + " " + result);
    }
  }

  public static void main(String[] args) {
    FactorialCalc factorialCalc = new FactorialCached();
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(new ClientTask("Client1", factorialCalc));
    executorService.execute(new ClientTask("Client2", factorialCalc));
    executorService.shutdown();
  }
}