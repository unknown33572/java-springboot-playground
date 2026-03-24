package com.study.java_springboot.java.generic_test;

public class Animal {
  private String name;
  private int age;

  public Animal(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void sound() {
    System.out.printf("%s가 소리를 냅니다.\n", name);
  }
}
