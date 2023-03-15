package com.epam.lesson1;

import javax.swing.*;

public class Main {

  public static void main(String[] args) {
    int procCount = Runtime.getRuntime().availableProcessors();
    System.out.println(procCount);

    JFrame frame = new JFrame("Our application");
    frame.setSize(400, 400);
    frame.setLocation(100, 100);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    JMenuBar menuBar = new JMenuBar();
    JMenu menuHelp = new JMenu("Help");
    JMenuItem itemAbout = new JMenuItem("About program...");
    itemAbout.addActionListener(e -> onClickAbout());
    menuHelp.add(itemAbout);
    menuBar.add(menuHelp);
    frame.setJMenuBar(menuBar);

    frame.setVisible(true);
  }

  private static void onClickAbout() {
    NotePadRunner.startNotepad();
    System.out.println("Clicked about menu item");
  }
}