package com.study.java_springboot.collection;

public class MySet {

  private int[] elementData = new int[10];
  private int size = 0;

  public boolean add(int value) {
    if (contains(value)) {
      return false;
    }

    elementData[size] = value;
    size++;
    return true;
  }

  public boolean contains(int value) {
    for (int data : elementData) {
      if ( data == value) {
        return true;
      }
    }

//    for (int i = 0; i < elementData.length; i++) {
//      if (elementData[i] == value) return true;
//    }
    return false;
  }

  public int size() {
    return size;
  }
}
