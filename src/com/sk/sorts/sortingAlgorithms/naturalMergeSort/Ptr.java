package com.sk.sorts.sortingAlgorithms.naturalMergeSort;

class Ptr {
  Node node;
  Ptr next;
  int size;

  public Ptr(Node node) {
    this.node = node;
    this.size = 0;
  }
  public Ptr pointerTo(int rank, int ptrSize, Ptr ptrStartNode) {
    Ptr here = ptrStartNode;
    if (rank >=0 && rank<ptrSize) {
      // Move through each node in the neighbors linked list until reaching the desired node
      for (int i=0; i<rank; i++) {
        here = here.next;
      }
    } else {
      System.err.println("Invalid rank provided.");
    }
    return here;
  }

//  private void addPointer(Ptr ptr) {
//    if (next == null) {
//      next = ptr;
//      size++;
//    } else {
//      Ptr temp = next;
//      while (temp.next != null) {
//        temp = temp.next;
//      }
//      temp.next = ptr;
//      size++;
//    }
//  }

  public void setNext(Ptr next) {
    this.next = next;
  }

  public int getSize() {
    return size;
  }

  public Ptr getNext() {
    return next;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public Node getNode() {
    return node;
  }

  public void setNode(Node node) {
    this.node = node;
  }
}