//package com.sk.sorts.sortingAlgorithms;
//
//public  class NaturalMergeSorter
//{
//  private  int [] a;
//  private  int [] b;    // auxiliary array
//  private  int n;
//
//  public  void sort ( int [] a)
//  {
//    this .a = a;
//    n = a.length;
//    b = new  int [n];
//    naturalmergesort ();
//  }
//
//  private  boolean mergeruns ( int [] a, int [] b)
//  {
//    int i = 0, k = 0, x;
//    boolean asc = true ;
//
//    while (i <n)
//    {
//      k = i;
//      do x = a [i ++]; while (i <n && x <= a [i]);  // ascending part
//      while (i <n && x >= a [i]) x = a [i ++];      // descending part
//      merge (a, b, k, i-1, asc);
//      asc asc =!;
//    }
//    return k == 0;
//  }
//
//  private  void merge ( int [] a, int [] b, int lo, int hi, boolean asc)
//  {
//    int k = asc? lo: hi;
//    int c = asc? 1: -1;
//    int i = lo, j = hi;
//
//    // copy back the next largest element
//    // until i and j intersect
//    while (i <= j)
//    {
//      if (a [i] <= a [j])
//        b [k] = a [i ++];
//      else
//        b [k] = a [J--];
//      + k = c;
//    }
//  }
//
//  private  void naturalmergesort ()
//  {
//    // merge alternately from a to b and from b to a
//    while (! mergeruns (a, b) &! mergeruns (b, a));
//  }
//
//}     // end class NaturalMergeSorter
