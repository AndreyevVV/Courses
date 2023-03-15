package com.epam.lesson6;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main2 {

  static class YourTask implements Callable<Integer> {

    private final int delay;
    private final int result;

    YourTask(int delay, int result) {
      this.delay = delay;
      this.result = result;
    }

    @Override
    public Integer call() throws Exception {
      Thread.sleep(delay);
      return result;
    }
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    YourTask yourTask1 = new YourTask(4000, 1);
    YourTask yourTask2 = new YourTask(2000, 2);
    YourTask yourTask3 = new YourTask(3000, 3);
//    ExecutorService executorService = Executors.newSingleThreadExecutor();
    ExecutorService executorService = Executors.newCachedThreadPool();
    Integer resultAny = executorService
        .invokeAny(Arrays.asList(yourTask1, yourTask2, yourTask3));
    System.out.println(resultAny);
//    List<Future<Integer>> futures = executorService
//        .invokeAll(Arrays.asList(yourTask1, yourTask2, yourTask3));
//    for (Future<Integer> future : futures) {
//      System.out.println(future.isDone());
//      System.out.println(future.get());
//    }
  }
}