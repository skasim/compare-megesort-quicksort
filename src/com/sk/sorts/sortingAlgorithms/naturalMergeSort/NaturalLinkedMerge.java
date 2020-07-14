package com.sk.sorts.sortingAlgorithms.naturalMergeSort;

/**
 * This code in this class is taken from http://javabypatel.blogspot.com/2015/12/merge-sort-linked-list.html.
 *
 * Specifically, the code provided the methods for the recursion on two sorted lists and the printing of the linked list.
 * The code was modified to include the creation of a pointers linked list to mimic a natural merge sort that would be used
 * to store the runs of sorted arrays created in a natural merge sort.
 *
 * @author Jayesh Patel
 */
public class NaturalLinkedMerge {

  Node startNode;
  Ptr ptrStartNode;
  int ptrSize;

  public NaturalLinkedMerge() {
    ptrSize = 0;
  }

  public static Node naturalMergeSort(NaturalLinkedMerge linkedMerge) {
    linkedMerge.createPointersLinkedList();
    Node sortedList = linkedMerge.mergeSort();
    return sortedList;
  }
  // Modified code borrowed from online to create a pointers linked list that houses the run made that creates
  // linked lists in sorted order
  private void createPointersLinkedList() {
    Node temp = startNode;
    addPointer(new Ptr(temp));
    while(temp.getNext() != null) {
      Node prev = temp;
      temp = temp.getNext();
      if (temp.getData() < prev.getData()) {
        prev.setNext(null);
        addPointer(new Ptr(temp));
      }
    }
  }
  // Added the below to use the recursive method found online to create a sorted list
  private Node mergeSort() {
    Node sortedList = ptrStartNode.pointerTo(0, ptrSize, ptrStartNode).getNode();
    for (int i=1; i<ptrSize; i++) {
      sortedList = mergeTwoListRecursive(sortedList, ptrStartNode.pointerTo(i, ptrSize, ptrStartNode).getNode());
    }

    return sortedList;
  }
  // Added below to add a node to the linked list
  public void addNode(int data) {
    Node node = new Node(data);
    if (startNode == null) {
      startNode = node;
    } else {
      Node temp = startNode;
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.next = node;
    }
  }
  // Added below to add a pointer to the pointers linked list
  private void addPointer(Ptr ptr) {
    if (ptrStartNode == null) {
      ptrStartNode = ptr;
      ptrSize++;
    } else {
      Ptr temp = ptrStartNode;
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.next = ptr;
      ptrSize++;
    }
  }
  // The methods below are by Jayesh Patel
  //Recursive Approach for Merging Two Sorted List
  private Node mergeTwoListRecursive(Node leftStart, Node rightStart){
    if(leftStart==null)
      return rightStart;

    if(rightStart==null)
      return leftStart;

    Node temp;

    if(leftStart.getData()<rightStart.getData()){
      temp=leftStart;
      temp.setNext(mergeTwoListRecursive(leftStart.getNext(), rightStart));
    }else{
      temp=rightStart;
      temp.setNext(mergeTwoListRecursive(leftStart, rightStart.getNext()));
    }
    return temp;
  }

  public int[] printLinkList(Node startNode, int arraySize) {
    int[] outputArr = new int[arraySize];
    Node temp = startNode;
    int count = 0;
    while(temp!=null){
      outputArr[count] = temp.getData();
      temp = temp.getNext();
      count++;
    }
    return outputArr;
  }
}
