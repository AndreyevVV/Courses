package com.epam.lesson3;

public class NyThread extends Thread {

  public static void main(String[] args) {
    Thread thread = new Thread(new NyThread());
//        new Thread(new NyThread().start();

    thread.start();
//    thread = null;
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; i++) {
      System.out.println(i);
    }
  }
}