package com.epam.lesson15;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main1 {

  public static void main(String[] args) {
    List<String> list = new CopyOnWriteArrayList<>();
    list.add("A");
    list.add("B");
    list.add("C");
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
//      iterator.remove();
      list.add("D");
    }
    System.out.println(list);
  }
}