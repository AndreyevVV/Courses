package com.epam.lesson10.staticfactory;

import java.util.ArrayList;
import java.util.List;

public class StaticFactoryEx {

  public static void main(String[] args) {
    Agency agency = new Agency();
    SeniorProgrammer seniorProgrammer = SeniorProgrammer.createAndRegister(agency);
    seniorProgrammer.doWork();
  }
}

class Programmer {

  protected final List<String> languages = new ArrayList<>();

  protected Programmer() {
    languages.add("Java");
    languages.add(".NET");
  }

  public List<String> getLanguages() {
    return languages;
  }
}

class SeniorProgrammer extends Programmer {

  private SeniorProgrammer() {
    super();
    languages.add("Perl");
    languages.add("Go");
  }

  public static SeniorProgrammer createAndRegister(Agency agency) {
    SeniorProgrammer seniorProgrammer = new SeniorProgrammer();
    agency.register(seniorProgrammer);
    return seniorProgrammer;
  }

  public void doWork() {
    System.out.println("Working now");
  }
}

class Agency {

  public void register(Programmer programmer) {
    System.out.println("Registered a new programmer. Knows: " + programmer.getLanguages());
  }
}