package com.epam.lesson12;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Que {

  int[] vals = new int[10];
  int count;
  int putNdx;
  int getNdx;

  private final Lock lock = new ReentrantLock();
  private final Condition canPut = lock.newCondition();
  private final Condition canGet = lock.newCondition();

  public void put(int val) {
    try {
      lock.lock();
      while (isFull()) {
//        System.out.println("Full");
        try {
          canPut.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      count++;
      vals[putNdx++] = val;
      canGet.signal();
    } finally {
      lock.unlock();
    }
  }

  private boolean isFull() {
    return count == 10;
  }

  public int get() {
    try {
      lock.lock();
      while (isEmpty()) {
//        System.out.println("Empty");
        try {
          canGet.await();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      count--;
      int val = vals[getNdx++];
      canPut.signal();
      return val;
    } finally {
      lock.unlock();
    }
  }

  private boolean isEmpty() {
    return count == 0;
  }
}

class Producer implements Runnable {

  private final Que que;

  public Producer(Que que) {
    this.que = que;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Put " + i + " in que");
      que.put(i);
    }
  }
}

class Consumer implements Runnable {

  private final Que que;

  public Consumer(Que que) {
    this.que = que;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      int val = que.get();
      System.out.println("Got " + val + " from que");
    }
  }
}

public class Main {

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

    Que que = new Que();

    executorService.execute(new Producer(que));
    executorService.execute(new Consumer(que));

    executorService.shutdown();
  }
}