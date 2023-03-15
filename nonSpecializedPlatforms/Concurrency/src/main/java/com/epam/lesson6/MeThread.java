package com.epam.lesson6;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MeThread implements Runnable {

  private String str;

  MeThread(String str) {
    this.str = str;
  }

  @Override
  public void run() {
    System.out.println(str.toUpperCase());
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    MeThread thread = new MeThread("ocp");
    Future future = executorService.submit(thread);
    Integer tmp = (Integer) future.get();
    System.out.println(tmp);
    executorService.shutdown();
  }
}