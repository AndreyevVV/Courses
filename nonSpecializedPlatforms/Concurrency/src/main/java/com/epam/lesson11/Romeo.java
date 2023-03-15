package com.epam.lesson11;

public class Romeo extends Actor {

  private boolean wordsSaid;

  public Romeo(Object stage) {
    super(stage, "Romeo");
  }

  @Override
  protected void doPlay() {
    synchronized (stage) {
      say("I love you, Juliet");
      wordsSaid = true;
      stage.notify();
    }
  }

  public boolean isWordsSaid() {
    return wordsSaid;
  }
}