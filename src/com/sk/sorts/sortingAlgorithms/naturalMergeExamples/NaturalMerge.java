package com.sk.sorts.sortingAlgorithms.naturalMergeExamples;

public class NaturalMerge {
  // TODO https://stackoverflow.com/questions/37243200/java-natural-merge-sort-implementation
  private static Comparable[] aux;

  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length];
    sort(a, 0, a.length - 1);
  }

  public static boolean isSorted(Comparable[] a) {
    for (int i = 1; i < a.length; i += 1) {
      if (a[i - 1].compareTo(a[i]) > 0) {//changed operator to greater than
        return false;
      }
    }
    return true;
  }

  private static void sort(Comparable[] a, int lo, int hi) {
    int i = lo;
    int j = 0;
    int mid = 0;
    int az = 0;

    while (true) {
      i = 0;
      System.out.println("outter");
      while (i < a.length) {
        System.out.println("inner 1");
        if (i == a.length - 1) {
          break;
        } else if (a[i].compareTo(a[i + 1]) > 0) {//changed operator to greater than
          break;
        }
        i++;
      }

      j = i + 1;

      while (j < a.length) {
        System.out.println("inner 2");
        if (j == a.length - 1) {
          break;
        } else if (a[j].compareTo(a[j + 1]) > 0) {//changed operator to greater than
          break;
        }
        j++;
      }
      //      mid = lo + (j - lo) / 2;
      Merge(a, lo, i, j);
      lo = 0;

      if (isSorted(a)) {
        break;
      }
    }
  }

  public static void Merge(Comparable[] a, int lo, int mid, int hi) {
    int i = lo;
    int j = mid + 1;

    for (int k = lo; k <= hi; k++) {
      aux[k] = a[k];
    }

    for (int k = lo; k <= hi; k++) {
      if (i > mid) {
        a[k] = aux[j++];
      } else if (j > hi) {
        a[k] = aux[i++];
      } else if (aux[i].compareTo(aux[j]) > 0) {//changed the operator to greater than
        a[k] = aux[j++];
      } else {
        a[k] = aux[i++];
      }
    }
  }

  public static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
  }

  public static void main(String[] args) {
    Integer[] arr = {6, 4, 5, 7, 8, 3, 2, 1};
    sort(arr);
    show(arr);
  }
}
