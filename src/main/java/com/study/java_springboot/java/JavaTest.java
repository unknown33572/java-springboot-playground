package com.study.java_springboot.java;

public class JavaTest {
  public static void main(String[] args) {

    Parent parent = new Parent();
    parent.method1(); // Parent method1
    parent.method2(); // Parent method2

    Child child = new Child();
    child.method1(); // Child method1
    child.method2(); // Parent method2
    child.method3(); // Child method2

    Parent parent2 = new Child();
    parent2.method1(); // Child method1
    parent2.method2(); // Parent method2
    // parent2.method3(); // 컴파일 에러

    Child child2 = new Child();
    Parent parent3 = (Parent) child2;
    parent3.method1(); // Child method1
    parent3.method2(); // Parent method2
//    parent3.method3();

    Parent parent4 = new Child();
    Child child3 = (Child) parent4;
    child3.method1();
    child3.method2();
    child3.method3();
  }
}
