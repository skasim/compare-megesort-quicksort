package com.sk.sorts.sortingAlgorithms.naturalMergeSort;

public class NaturalLinkedMerge {

  Node startNode;
  Ptr[] ptrsList = new Ptr[100];
  Ptr ptrStartNode;
  int ptrSize;


  public static void main(String[] args) {
    new NaturalLinkedMerge();
  }

  public NaturalLinkedMerge() {
    Node node1 = new Node(5);
    Node node2 = new Node(8);
    Node node3 = new Node(13);
    Node node4 = new Node(16);
    Node node5 = new Node(2);
    Node node6 = new Node(6);
    Node node7 = new Node(10);
    Node node8 = new Node(14);
    Node node9 = new Node(11);
    Node node10 = new Node(1);
    Node node11 = new Node(4);
    Node node12 = new Node(15);

    node1.setNext(node2);
    node2.setNext(node3);
    node3.setNext(node4);
    node4.setNext(node5);
    node5.setNext(node6);
    node6.setNext(node7);
    node7.setNext(node8);
    node8.setNext(node9);
    node9.setNext(node10);
    node10.setNext(node11);
    node11.setNext(node12);

//    Node node1 = new Node(1);
//    Node node2 = new Node(3);
//    Node node3 = new Node(5);
//    Node node4 = new Node(7);
//    Node node5 = new Node(8);
//    Node node6 = new Node(22);
//    Node node7 = new Node(35);
//
//    node1.setNext(node2);
//    node2.setNext(node3);
//    node3.setNext(node4);
//    node4.setNext(node5);
//    node5.setNext(node6);
//    node6.setNext(node7);
//
//    Node otherList1 = new Node(2);
//    Node otherList2 = new Node(3);
//    Node otherList3 = new Node(9);
//    Node otherList4 = new Node(88);
//
//    otherList1.setNext(otherList2);
//    otherList2.setNext(otherList3);
//    otherList3.setNext(otherList4);

    startNode = node1;
    ptrSize = 0;

//    printLinkList(startNode);
//    System.out.println();
//    createPointersList();
//
//    System.out.println();
//    System.out.println("zero node");
//    printLinkList(ptrsList[0].getNode());
//
//
//    System.out.println();
//    System.out.println("one node");
//    printLinkList(ptrsList[1].getNode());
//
//    System.out.println();
//    System.out.println("two node");
//    printLinkList(ptrsList[2].getNode());
//
//    System.out.println();
//    System.out.println("three node");
//    printLinkList(ptrsList[3].getNode());
//
//    System.out.println();
//    System.out.println("REPRINT LIST");
//    printLinkList(startNode);

//    Node leftStart = node1;
//    Node rightStart = otherList1;

//    Node sortedStartNode = mergeSortLinkList(startNode);

//    Node merged = mergeTwoListRecursive(leftStart, rightStart);
//    printLinkList(merged);

//    printLinkList(sortedStartNode);



    printLinkList(startNode);

    createPointersLinkedList();
    System.out.println("SIZE: " + ptrSize);
    System.out.println();
    System.out.println("zero node");
    printLinkList(ptrStartNode.pointerTo(0, ptrSize, ptrStartNode).getNode());
    System.out.println();
    System.out.println("one node");
    printLinkList(ptrStartNode.pointerTo(1, ptrSize, ptrStartNode).getNode());

    System.out.println();
    System.out.println("two node");
    printLinkList(ptrStartNode.pointerTo(2, ptrSize, ptrStartNode).getNode());

    System.out.println();
    System.out.println("three node");
    printLinkList(ptrStartNode.pointerTo(3, ptrSize, ptrStartNode).getNode());

    System.out.println();
    System.out.println("REPRINT LIST");
    printLinkList(startNode);



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


  private void addPointer(Ptr ptr) {
    if (ptrStartNode == null) {
      ptrStartNode = ptr;
      ptrSize++;
    } else {
      System.out.println();
      Ptr temp = ptrStartNode;
      while (temp.next != null) {
        temp = temp.next;
      }
      temp.next = ptr;
      System.out.println(ptrSize);
      ptrSize++;
    }

    System.out.println("printing linked list");
    System.out.println("size: " + ptrSize);
    printPtrLinkList(ptrStartNode);
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
    printPtrList(ptrsList);
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
