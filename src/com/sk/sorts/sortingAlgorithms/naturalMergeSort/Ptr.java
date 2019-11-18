package com.sk.sorts.sortingAlgorithms.naturalMergeSort;

/**
 * A class that creates a linked list of pointers. It features stores the Node object and has a pointer to the next node
 * in hte pointers linked list. It also stores size information about the size of the linked list.
 *
 * @author Samra Kasim
 */
public class Ptr {
  Node node;
  Ptr next;
  int size;

  // constuctor
  public Ptr(Node node) {
    this.node = node;
    this.size = 0;
  }

  /**
   * Provide random access to the nodes in a linked list.
   * @param rank: int value representing location of the node.
   * @param ptrSize: int value representing size of linked list.
   * @param ptrStartNode: Ptr object representing hte linked list.
   * @return
   */
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