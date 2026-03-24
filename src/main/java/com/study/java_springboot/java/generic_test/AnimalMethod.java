package com.study.java_springboot.java.generic_test;

public class AnimalMethod {
  public static <T extends Animal> void checkup(T t) {
    System.out.println("동물 이름:" + t.getName());
    System.out.println("동물 나이:" + t.getAge());
    t.sound();
  }

  public static <T extends Animal> T bigger(T t1, T t2) {
    if (t1.getAge() > t2.getAge()) {
      return t1;
    } else {
      return t2;
    }
  }
}
