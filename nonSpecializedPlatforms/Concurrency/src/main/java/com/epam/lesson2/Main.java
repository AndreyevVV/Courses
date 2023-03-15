package com.epam.lesson2;

public class Main {

  public static void main(String[] args) {
//    System.out.println("Hello World");
//    System.out.println("Hello World 2");
//    System.out.println("Hello World 3");
    ///--------
//    System.out.println(Thread.currentThread().getName());
//    System.out.println(Thread.activeCount());
//    Thread[] threads = new Thread[Thread.activeCount()];
//    Thread.enumerate(threads);

//    for (Thread thread : threads) {
//      System.out.println(thread.getName());
//      System.out.println(thread.isDaemon());
//    }

    Thread starship1 = new Starship();
    starship1.setName("starship1");
    starship1.setDaemon(true);
    starship1.start();

    Thread starship2 = new Starship();
    starship2.setName("starship2");
    starship2.start();

    Thread shuttle = new Thread(new Shuttle());
    shuttle.start();

    System.out.println("Main exited");

    Thread starshipThread = new MyThread(new Starship());
    starshipThread.start();
  }
}

class Starship extends Thread {

//  public Starship(String name) {
//    super(name);
//  }

  @Override
  public void run() {
    System.out.println("final countdown");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("launched");
  }
}

class Shuttle implements Runnable {

  @Override
  public void run() {
    System.out.println("final shuttle");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("launch shuttle");
  }

  public void launch() {
    Thread runner = new Thread(this);
    runner.start();
  }
}

class MyThread extends Thread {

  MyThread(Runnable r) {
    super(r);
  }

  @Override
  public void run() {
    System.out.println("My thread run");
  }
}