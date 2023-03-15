package com.epam.lesson11;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class Actor implements Runnable {

  private static final ExecutorService executorService = Executors.newCachedThreadPool();

  private final String name;
  protected final Object stage;

  public Actor(Object stage, String name) {
    this.stage = stage;
    this.name = name;
  }

  protected void say(String words) {
    System.out.println(name + ": " + words);
  }

  @Override
  public void run() {
    doPlay();
  }

  protected abstract void doPlay();

  public void play() {
    executorService.execute(this);
  }

  static void endPlay() {
    executorService.shutdown();
  }
}