package com.epam.lesson14;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Downloader {

  private final Semaphore semaphore = new Semaphore(3);

  public void downloadPopularImage() throws InterruptedException {
    semaphore.acquire();
    System.out.println("Downloading");
    Thread.sleep(3000);
    semaphore.release();
  }
}

class SemaphoreEx {

  private static Downloader downloader = new Downloader();
  private static ExecutorService service = Executors.newCachedThreadPool();

  public static void main(String[] args) {
    for (int i = 0; i < 21; i++) {
      service.submit(() -> {
        try {
          downloader.downloadPopularImage();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
    service.shutdown();
  }
}