package com.study.java_springboot.java.generic_test;

public class ComplexBox<T extends Animal> {

  private T animal;

  public void set(T animal) {
    this.animal = animal;
  }

  public <Z> Z printAndReturn(Z z) {
    System.out.println("동물 클래스이름:" + animal.getClass().getName());
    System.out.println("동물 타입클래스이름:" + z.getClass().getName());
//    animal.sound();
    return z;
  }
}
