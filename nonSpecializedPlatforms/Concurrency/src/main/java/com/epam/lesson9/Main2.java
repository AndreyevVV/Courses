package com.epam.lesson9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main2 {

  public synchronized void myMethod() {
    System.out.println("Enter my method");
    myMethod();
  }

  public static void main(String[] args) {
//    Main2 main2 = new Main2();
//    main2.myMethod();

    Spaceship spaceship = new Spaceship();
    System.out.println(spaceship.getCrew());
    spaceship.getCrew().add("Alien");
    System.out.println(spaceship.getCrew());
    spaceship.getCrew().remove(0);
  }
}

class Spaceship {

  private final List<String> crew = new ArrayList<>();

  Spaceship() {
    crew.add("Tom");
    crew.add("Jack");
    crew.add("John");
  }

  public List<String> getCrew() {
    return Collections.unmodifiableList(crew);
  }
}