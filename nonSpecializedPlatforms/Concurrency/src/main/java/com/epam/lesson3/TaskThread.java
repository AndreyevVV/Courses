package com.epam.lesson3;

class TaskThread extends Thread {

  TaskThread() {
  }

  TaskThread(Runnable r) {
    super(r);
  }

  @Override
  public void run() {
    System.out.println("Inside thread");
  }
}

class RunnableDemo implements Runnable {

  @Override
  public void run() {
    System.out.println("Inside Runnable");
  }
}