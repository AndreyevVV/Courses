package com.epam.lesson3;

public class MyTask implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println("Counter: " + i);
    }
  }
}

class Main1 {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Hello world");
    MyTask myTask = new MyTask();
    Thread thread = new Thread(myTask);
    thread.start();
    thread.join();
    System.out.println("Main exited");
  }
}