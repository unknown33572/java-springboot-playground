package com.study.java_springboot.collection;

import java.util.LinkedList;

public class MyHashSet {

  static final int DEFAULT_INITIAL_CAPACITY = 16;

  LinkedList<Integer>[] buckets;

  private int size = 0;
  private int capacity = DEFAULT_INITIAL_CAPACITY;

  public MyHashSet() {
    buckets = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      buckets[i] = new LinkedList<>();
    }
  }

  public MyHashSet(int capacity) {
    this.capacity = capacity;
    buckets = new LinkedList[capacity];
    for (int i = 0; i < capacity; i++) {
      buckets[i] = new LinkedList<>();
    }
  }

  public boolean add(int value) {
    int hashIndex = hashIndex(value);
    LinkedList<Integer> bucket = buckets[hashIndex];
    if (bucket.contains(value)) {
      return false;
    }

    bucket.add(value);
    size++;
    return true;
  }

  public boolean contains(int searchValue) {
    int hashIndex = hashIndex(searchValue);
    LinkedList<Integer> bucket = buckets[hashIndex];
    return bucket.contains(searchValue);
  }

  public boolean remove(int value) {
    int hashIndex = hashIndex(value);
    LinkedList<Integer> bucket = buckets[hashIndex];
    bucket.remove(Integer.valueOf(value)); // int 를 그대로 넘기면 인덱스를 찾아서 지워버리기 때문에 Integer 객체로 만들어서 넘겨야 한다.
    size--;
    return true;
  }

  private int hashIndex(int value) {
    return value % capacity;
  }
}
