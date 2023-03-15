package com.epam.lesson3;

public class NewThread extends Thread {

  @Override
  public void run() {
    System.out.println("Started");
    for (int i = 0; i < 100000; i++) {

    }
    try {
      System.out.println("Sleeping");
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Done");
  }
}

class Execute {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new NewThread();
    System.out.println("State: " + thread.getState());
    thread.start();
    System.out.println("State: " + thread.getState());
    while (true) {
      Thread.currentThread().sleep(1000);
      System.out.println("State: " + thread.getState());
    }
  }
}