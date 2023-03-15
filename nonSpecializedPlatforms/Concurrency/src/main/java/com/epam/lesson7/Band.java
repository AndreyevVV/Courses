package com.epam.lesson7;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class Band implements Callable<BandResult> {

  private final String name;
  private final List<String> songs;
  private final int songLength;

  public Band(String name, List<String> songs, int songLength) {
    this.name = name;
    this.songs = songs;
    this.songLength = songLength;
  }

  public Future<BandResult> play(Festival festival) {
    return festival.accept(this);
  }

  @Override
  public BandResult call() throws InterruptedException {
    for (String song : songs) {
      System.out.println(name + " " + Thread.currentThread().getName() + " исполняется: " + song);
      Thread.sleep(songLength);
    }
    return new BandResult(name, 1000);
  }
}