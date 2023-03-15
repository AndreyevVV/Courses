package com.epam.lesson3;

class MyThread1 extends Thread {

  @Override
  public void run() {
    System.out.println("MyThread1");
  }
}

class MyThread2 extends Thread {

  @Override
  public void run() {
    System.out.println("MyThread2");
  }
}

class Main2 {

  public static void main(String[] args) throws InterruptedException {
    Thread thread1 = new MyThread1();
    Thread thread2 = new MyThread2();
    thread2.setPriority(Thread.MIN_PRIORITY);
    thread1.setPriority(Thread.MAX_PRIORITY);
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
  }
}