package com.epam.lesson3;

class Candidate implements Runnable {

  private Thread t;

  private final String[] languages = {
      "C++",
      "Java",
      "JavaScript",
      "Go",
      "Cobol",
      "Perl"
  };

  @Override
  public void run() {
    while (!Thread.interrupted()) {
      System.out.println("I know languages: ");
      for (String language : languages) {
        System.out.println(language);
      }
    }
    System.out.println("OK...");
  }

  public void talk() {
    t = new Thread(this);
    t.start();
  }

  public void requestStop() {
    t.interrupt();
  }
}

class Main4 {

  public static void main(String[] args) throws InterruptedException {
    Candidate candidate = new Candidate();
    candidate.talk();
    Thread.sleep(1000);
    candidate.requestStop();
  }
}