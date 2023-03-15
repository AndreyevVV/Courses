package com.epam.lesson3;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new TaskThread();
    thread1.start();
    Thread thread2 = new TaskThread(new RunnableDemo());
    thread2.start();

    thread1.join();
    thread2.join();
  }
}