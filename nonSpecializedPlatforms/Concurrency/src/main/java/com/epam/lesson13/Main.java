package com.epam.lesson13;

public class Main {

  public static void main(String[] args) {
    Thread thread = new Thread() {
      @Override
      public void run() {
        synchronized (this) {

        }
      }
    };
    thread.start();
//    thread.start();
  }
}