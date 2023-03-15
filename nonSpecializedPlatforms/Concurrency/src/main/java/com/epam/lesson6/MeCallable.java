package com.epam.lesson6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MeCallable implements Callable<Integer> {

  private Integer i;

  public MeCallable(Integer i) {
    this.i = i;
  }

  @Override
  public Integer call() throws Exception {
    return --i;
  }
}

class MyThread extends Thread {

  private int i;

  public MyThread(int i) {
    this.i = i;
  }

  @Override
  public void run() {
    i++;
  }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    MeCallable callable = new MeCallable(10);
    MyThread thread = new MyThread(10);
    System.out.println(executorService.submit(callable).get());
    System.out.println(executorService.submit(thread).get());
    executorService.shutdown();
  }
}