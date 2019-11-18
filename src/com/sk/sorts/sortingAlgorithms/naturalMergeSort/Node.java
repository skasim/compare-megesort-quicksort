package com.sk.sorts.sortingAlgorithms.naturalMergeSort;

/**
 * A node class that contains a data object that stores the integer value found in an array and a pointer to the next
 * Node.
 *
 * @author Samra Kasim
 */
public class Node {
  int data;
  Node next;

  public Node(int data) {
    this.data = data;
  }


  public void setNext(Node next) {
    this.next = next;
  }

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

  public Node getNext() {
    return next;
  }
}
