package com.epam.lesson11;

public class Main {

  public static void main(String[] args) {
    final Object stage = new Object();

    Romeo romeo = new Romeo(stage);
    romeo.play();

    Juliet juliet = new Juliet(stage, romeo);
    juliet.play();

    Actor.endPlay();
  }
}