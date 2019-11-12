package com.sk.sorts.sortingAlgorithms.naturalMergeExamples;

public class jsortns {
  private static Comparable[] aux;

  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length];
    int i;
    int j;
    int k;
    while (true) {                  // merge pass
      i = 0;
      while (true) {               // find, merge pair of runs
        j = i;                  // find left run
        while (++j < a.length) {
          if (a[j - 1].compareTo(a[j]) > 0) break;
        }
        if (j == a.length) {      // if only one run left
          if (i == 0)          //   if done return
            return;
          else                //   else end of merge pass
            break;
        }
        k = j;                  // find right run
        while (++k < a.length) {
          if (a[k - 1].compareTo(a[k]) > 0) {
            break;
          }
        }
        Merge(a, i, j, k);      // merge runs
        i = k;
        if (i == a.length)       // if end of merge pass, break
          break;
      }
    }
  }

  // merge left and right runs
  // ll = start of left run
  // rr = start of right run == end of left run
  // ee = end of right run
  public static void Merge(Comparable[] a, int ll, int rr, int ee) {
    int i = ll;
    int j = rr;
    int k;
    for (k = ll; k < ee; k++)
      aux[k] = a[k];
    k = ll;
    while (true) {
      // if left element <= right element
      if (aux[i].compareTo(aux[j]) <= 0) {
        a[k++] = aux[i++];      // copy left element
        if (i == rr) {            // if end of left run
          while (j < ee)       //   copy rest of right run
            a[k++] = aux[j++];
          return;                 //   and return
        }
      } else {
        a[k++] = aux[j++];      // copy right element
        if (j == ee) {            // if end of right run
          while (i < rr) {      //   copy rest of left run
            a[k++] = aux[i++];
          }
          return;                 //   and return
        }
      }
    }
  }
}