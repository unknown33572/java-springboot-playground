package com.study.java_springboot.collection;

public class MyLinkedList<E> implements MyList<E> {

  private Node<E> head;
  private int size = 0;

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(E e) {
    Node<E> newNode = new Node<>(e);
    if (head == null) {
      head = newNode;
    } else {
      Node<E> lastNode = getLastNode();
      lastNode.next = newNode;
    }
    size++;
  }

  private Node<E> getLastNode() {
    Node<E> currentNode = head;
    while (currentNode.next != null) {
      currentNode = currentNode.next;
    }
    return currentNode;
  }

  @Override
  public void add(int idx, E e) {
    checkIndex(idx);
    Node<E> newNode = new Node<>(e);

    if (idx == 0) {
      newNode.next = head;
      head = newNode;
    }
  }

  private void checkIndex(int idx) {
    if (idx < 0 || idx >= size) {
      throw new IndexOutOfBoundsException("idx: " + idx + ", size: " + size);
    }
  }

  @Override
  public E get(int idx) {
    return null;
  }

  @Override
  public E set(int idx, E element) {
    return null;
  }

  @Override
  public E remove(int idx) {
    return null;
  }

  @Override
  public int indexOf(E o) {
    return 0;
  }

  static class Node<E> {
    private E data;
    private Node<E> next;

    public Node(E data) {
      this.data = data;
    }

    public E getData() {
      return data;
    }

    public void setData(E data) {
      this.data = data;
    }

    public Node<E> getNext() {
      return next;
    }

    public void setNext(Node<E> next) {
      this.next = next;
    }
  }
}
