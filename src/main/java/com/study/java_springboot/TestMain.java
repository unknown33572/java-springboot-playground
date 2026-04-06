package com.study.java_springboot;

import com.study.java_springboot.collection.MySet;

public class TestMain {
  public static void main(String[] args) {
    MySet mySet = new MySet();

    mySet.add(1);
    mySet.add(2);
    mySet.add(3);
    mySet.add(2); // 중복된 값 추가 시도

    System.out.println("Set의 크기: " + mySet.size()); // 중복된 값은 추가되지 않으므로 크기는 3이어야 함
  }
}
