package com.study.java_springboot.java.generic_test;

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog("멍멍이", 3);
    Cat cat = new Cat("야옹이", 2);
//
//    AnimalMethod.<Dog>checkup(dog);
//    AnimalMethod.checkup(cat); // 제네릭 메서드 호출 시 타입 추론을 통해 생략 가능
//
//    Dog olderDog = new Dog("짖는이", 5);
//
//    Animal result = AnimalMethod.bigger(dog, olderDog);
//
//    System.out.println("나이가 더 많은 개: " + result.getName() + ", 나이: " + result.getAge());

    ComplexBox<Dog> hospital = new ComplexBox<>();
    hospital.set(dog);

    Cat returnCat = hospital.printAndReturn(cat);
    System.out.println("반환된 고양이: " + returnCat);
  }
}
