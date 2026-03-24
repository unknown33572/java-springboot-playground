package com.study.java_springboot.collection;

public interface MyList<E> {

  int size();

  void add(E e);

  void add(int idx, E e);

  E get(int idx);

  E set(int idx, E element);

  E remove(int idx);

  int indexOf(E o);
}
