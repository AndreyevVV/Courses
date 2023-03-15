package com.epam.lesson11;

public class Juliet extends Actor {

  private final Romeo romeo;

  public Juliet(Object stage, Romeo romeo) {
    super(stage, "Juliet");
    this.romeo = romeo;
  }

  @Override
  protected void doPlay() {
    synchronized (stage) {
      say("Good evening");
      while (!romeo.isWordsSaid()) {
        try {
          stage.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      say("I love you too");
    }
  }
}