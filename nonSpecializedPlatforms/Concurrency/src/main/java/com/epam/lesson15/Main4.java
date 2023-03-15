package com.epam.lesson15;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main4 {

  public static void main(String[] args) throws InterruptedException {
    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    map.put("test", 0);

    ExecutorService service = Executors.newCachedThreadPool();
    for (int i = 0; i < 10; i++) {
      service.execute(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < 10; i++) {
//            map.computeIfAbsent();
//            map.putIfAbsent();
            map.compute("test", (key, value) -> value + 1);
          }
        }
      });
    }
    service.shutdown();
    service.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    System.out.println("Result: " + map.get("test"));
  }
}