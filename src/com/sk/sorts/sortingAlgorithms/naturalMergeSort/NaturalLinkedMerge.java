package com.sk.sorts.sortingAlgorithms.naturalMergeSort;

public class NaturalLinkedMerge {
  // TODO http://javabypatel.blogspot.com/2015/12/merge-sort-linked-list.html
  public Node startNode;
  Ptr[] ptrsList = new Ptr[100];
  public Ptr ptrStartNode;
  public int ptrSize;


  public static void main(String[] args) {
//    NaturalLinkedMerge linkedMerge= new NaturalLinkedMerge();
//    linkedMerge.addNode(5);
//    linkedMerge.addNode(8);
//    linkedMerge.addNode(13);
//    linkedMerge.addNode(16);
//    linkedMerge.addNode(2);
//    linkedMerge.addNode(6);
//    linkedMerge.addNode(10);
//    linkedMerge.addNode(14);
//    linkedMerge.addNode(11);
//    linkedMerge.addNode(1);
//    linkedMerge.addNode(4);
//    linkedMerge.addNode(15);
//    linkedMerge.printLinkList(linkedMerge.startNode);
//    linkedMerge.createPointersLinkedList();
//    Node sl = linkedMerge.ptrStartNode.getNode();
//    Node sortedList = linkedMerge.mergeSort(sl, linkedMerge.ptrStartNode);
//    System.out.println("\n\nfinal\n");
//    linkedMerge.printLinkList(sortedList);
  }

  public NaturalLinkedMerge() {
    ptrSize = 0;
  }


  public void createPointersLinkedList() {
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

  public Node mergeSort() {
    Node sortedList = ptrStartNode.pointerTo(0, ptrSize, ptrStartNode).getNode();
    for (int i=1; i<ptrSize; i++) {
      sortedList = mergeTwoListRecursive(sortedList, ptrStartNode.pointerTo(i, ptrSize, ptrStartNode).getNode());
    }

    return sortedList;
  }

  public Node mergeSort(Node sortedList, Ptr startPtr) {
    if (startPtr == null|| startPtr.getNext() == null) {
      return sortedList;
    }

    Node right = startPtr.getNext().getNode();
    sortedList = mergeSort(mergeTwoListRecursive(sortedList, right), startPtr.getNext());
    return sortedList;
  }

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

  public int[] printLinkList(Node startNode, int arraySize) {
    int[] outputArr = new int[arraySize];
    Node temp = startNode;
    int count = 0;
    while(temp!=null){
//      System.out.print(temp.getData() + " ");
      outputArr[count] = temp.getData();
      temp = temp.getNext();
      count++;
    }
    return outputArr;
  }

  private void printPtrLinkList(Ptr startNode) {
    Ptr temp = startNode;
    while(temp!=null){
      System.out.print(temp.getNode().getData() + " ");
      temp = temp.getNext();
    }
  }

  private void printPtrList(Ptr[] ptrs) {
    for (int i=0; i<ptrs.length; i++) {
      if (ptrs[i] !=null) {
        System.out.print(ptrs[i].getNode().getData() +" ");
      }
    }
  }
}
