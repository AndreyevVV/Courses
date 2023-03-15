package com.epam.lesson1;

import java.io.IOException;

public class NotePadRunner {

  public static void startNotepad() {
    ProcessBuilder pb = new ProcessBuilder("notepad.exe", "readme.txt");
    try {
      pb.start();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Clicked about menu item");
  }
}