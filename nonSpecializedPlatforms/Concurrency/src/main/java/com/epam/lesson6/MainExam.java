package com.epam.lesson6;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainExam {

  private static void print() {
    System.out.println("PRINT");
  }

  private static Integer get() {
    return 10;
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    Future<?> future1 = executorService.submit(MainExam::print);
    Future<?> future2 = executorService.submit(MainExam::get);
    System.out.println(future1.get());
    System.out.println(future2.get());
    executorService.shutdown();
  }
}