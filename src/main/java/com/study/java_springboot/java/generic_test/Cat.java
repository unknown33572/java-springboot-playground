package com.study.java_springboot.java.generic_test;

public class Cat extends Animal {
  private String name;
  private int age;

  public Cat(String name, int age) {
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
    System.out.printf("%s가 야옹 소리를 냅니다.\n", name);
  }

}
