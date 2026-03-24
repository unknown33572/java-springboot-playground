package com.study.java_springboot.collection;

public class MyArrayList<E> implements MyList<E> {

  private static final int DEFAULT_CAPACITY = 5;

  private Object[] elementData;
  int size = 0;

  public MyArrayList() { // 기본 생성자
    elementData = new Object[DEFAULT_CAPACITY];
  }

  public MyArrayList(int initialCapacity) { // 초기 용량을 받는 생성자
    elementData = new Object[initialCapacity];
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(E e) {
    if (size == elementData.length) {
      scaleUp();
    }
    elementData[size] = e;
    size++;
  }

  @Override
  public void add(int idx, E e) {
    if (size == elementData.length) {
      scaleUp();
    }
    shiftFrom(idx);
    elementData[idx] = e;
    size++;
  }

  private void shiftFrom(int idx) { // idx부터 오른쪽으로 한 칸씩 이동
    int i = size;
    while (i > idx) {
      elementData[i] = elementData[i - 1];
      i--;
    }
  }

  private void scaleUp() {
    int newCapacity = elementData.length * 2;
    Object[] newArray = new Object[newCapacity];

    for (int i = 0; i < elementData.length; i++) {
      newArray[i] = elementData[i];
    }

    elementData = newArray;
  }


  @Override
  @SuppressWarnings("unchecked")
  public E get(int idx) {
    return (E) elementData[idx];
  }

  @Override
  public E set(int idx, E element) {
    E oldValue = get(idx);
    elementData[idx] = element;
    return oldValue;
  }

  @Override
  public E remove(int idx) {
    E oldValue = get(idx);
    shiftLeftFrom(idx);

    size--;
    elementData[size] = null;
    return null;
  }

  private void shiftLeftFrom(int idx) { // idx부터 왼쪽으로 한 칸씩 이동
    int i = 0;
    while (i < size - 1) {
      elementData[i] = elementData[i + 1];
      i++;
    }
    elementData[size - 1] = null;
    size--;
  }

  @Override
  public int indexOf(E o) {
    for (int i = 0; i < size; i++) {
      if (elementData[i].equals(o)) {
        return i;
      }
    }
    return -1;
  }
}
