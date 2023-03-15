package com.epam.lesson12;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class Person implements Comparable {

  private final String name;
  private final boolean isFemale;
  private final int age;

  Person(String name, boolean isFemale, int age) {
    this.name = name;
    this.isFemale = isFemale;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", isFemale=" + isFemale +
        ", age=" + age +
        '}';
  }

  @Override
  public int compareTo(Object o) {
    Person other = (Person) o;
    if (other.isFemale && !isFemale) {
      return 1;
    }
    return other.age - age;
  }
}

public class PriorityEx {

  public static void main(String[] args) throws InterruptedException {
    BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
    queue.put(new Person("Lena", true, 20));
    queue.put(new Person("Misha", false, 40));
    queue.put(new Person("Tanya", true, 30));
    queue.put(new Person("Kolya", false, 33));

    System.out.println(queue.take());
    System.out.println(queue.take());
    System.out.println(queue.take());
    System.out.println(queue.take());
  }
}