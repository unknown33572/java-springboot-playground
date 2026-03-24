package com.study.java_springboot.java.generic_test;

public class Dog extends Animal {
  private String name;
  private int age;

  public Dog(String name, int age) {
    super(name, age);
    this.name = name;
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  @Override
  public void sound() {
    System.out.printf("%s가 멍멍 소리를 냅니다.\n", name);
  }
}
