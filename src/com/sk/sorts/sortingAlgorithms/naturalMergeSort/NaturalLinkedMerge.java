package com.sk.sorts.sortingAlgorithms.naturalMergeSort;

public class NaturalLinkedMerge {

  Node startNode;
  Ptr[] ptrsList = new Ptr[100];
  Ptr ptrStartNode;
  int ptrSize;


  public static void main(String[] args) {
    NaturalLinkedMerge linkedMerge= new NaturalLinkedMerge();
    linkedMerge.addNode(5);
    linkedMerge.addNode(8);
    linkedMerge.addNode(13);
    linkedMerge.addNode(16);
    linkedMerge.addNode(2);
    linkedMerge.addNode(6);
    linkedMerge.addNode(10);
    linkedMerge.addNode(14);
    linkedMerge.addNode(11);
    linkedMerge.addNode(1);
    linkedMerge.addNode(4);
    linkedMerge.addNode(15);
    linkedMerge.printLinkList(linkedMerge.startNode);
    linkedMerge.createPointersLinkedList();
    Node sl = linkedMerge.ptrStartNode.getNode();
    Node sortedList = linkedMerge.mergeSort(sl, linkedMerge.ptrStartNode);
    System.out.println("\n\nfinal\n");
    linkedMerge.printLinkList(sortedList);
  }

  public NaturalLinkedMerge() {
//    Node node1 = new Node(5);
//    Node node2 = new Node(8);
//    Node node3 = new Node(13);
//    Node node4 = new Node(16);
//    Node node5 = new Node(2);
//    Node node6 = new Node(6);
//    Node node7 = new Node(10);
//    Node node8 = new Node(14);
//    Node node9 = new Node(11);
//    Node node10 = new Node(1);
//    Node node11 = new Node(4);
//    Node node12 = new Node(15);
//
//    node1.setNext(node2);
//    node2.setNext(node3);
//    node3.setNext(node4);
//    node4.setNext(node5);
//    node5.setNext(node6);
//    node6.setNext(node7);
//    node7.setNext(node8);
//    node8.setNext(node9);
//    node9.setNext(node10);
//    node10.setNext(node11);
//    node11.setNext(node12);

//    startNode = node1;
    ptrSize = 0;

//    createPointersLinkedList();
//    Node sl = ptrStartNode.getNode();
//    Node sortedList = mergeSort(sl, ptrStartNode);
//    System.out.println("\n\nfinal\n");
//    printLinkList(sortedList);

//    Node sortedListIter = mergeSort();
//    System.out.println("\n\nfinal\n");
//    printLinkList(sortedListIter);
  }


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

  private Node mergeSort() {
    Node sortedList = ptrStartNode.pointerTo(0, ptrSize, ptrStartNode).getNode();
    for (int i=1; i<ptrSize; i++) {
      sortedList = mergeTwoListRecursive(sortedList, ptrStartNode.pointerTo(i, ptrSize, ptrStartNode).getNode());
    }

    return sortedList;
  }

  private Node mergeSort(Node sortedList, Ptr startPtr) {
    if (startPtr == null|| startPtr.getNext() == null) {
      return sortedList;
    }

    Node right = startPtr.getNext().getNode();
    sortedList = mergeSort(mergeTwoListRecursive(sortedList, right), startPtr.getNext());
    return sortedList;
  }

  private void addNode(int data) {
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

  private void createPointersList() {
    Node temp = startNode;
    int i=0;
    ptrsList[i] = new Ptr(temp);
    while(temp.getNext() != null) {
      Node prev = temp;
      temp = temp.getNext();
      if (temp.getData() < prev.getData()) {
        prev.setNext(null);
        i++;
        Ptr ptr = new Ptr(temp);
        ptrsList[i] = ptr;
      }
    }
  }
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

  private void printLinkList(Node startNode) {
    Node temp = startNode;
    while(temp!=null){
      System.out.print(temp.getData() + " ");
      temp = temp.getNext();
    }
  }

  private void printPtrLinkList(Ptr startNode) {
    Ptr temp = startNode;
    while(temp!=null){
      System.out.print(temp.getNode().getData() + " ");
      temp = temp.getNext();
    }
  }

  private void printPtrList(Ptr[] ptrs) {
    System.out.println("PRINTING PTR LIST");
    for (int i=0; i<ptrs.length; i++) {
      if (ptrs[i] !=null) {
        System.out.print(ptrs[i].getNode().getData() +" ");
      }
    }
  }
}
