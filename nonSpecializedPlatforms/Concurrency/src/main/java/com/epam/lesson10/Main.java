package com.epam.lesson10;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    Agency agency = new Agency();
    SeniorProgrammer seniorProgrammer = new SeniorProgrammer(agency);
  }
}

class Programmer {

  protected List<String> languages = new ArrayList<>();

  Programmer(Agency agency) {
    languages.add("Java");
    languages.add(".NET");
    agency.register(this);
  }

  public List<String> getLanguages() {
    return languages;
  }
}

class SeniorProgrammer extends Programmer {

  SeniorProgrammer(Agency agency) {
    super(agency);
    languages.add("Perl");
    languages.add("Go");
  }
}

class Agency {

  public void register(Programmer programmer) {
    System.out.println("Registered a new programmer. Knows: " + programmer.getLanguages());
  }
}