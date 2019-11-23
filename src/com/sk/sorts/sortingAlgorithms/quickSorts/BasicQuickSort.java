package com.sk.sorts.sortingAlgorithms.quickSorts;

import java.util.Arrays;

/**
 * Java program to Sort integer array using QuickSort algorithm using recursion.
 * Recursive QuickSort algorithm, partitioned list into two parts by a pivot,
 * and then recursively sorts each list. It is from https://www.java67.com/2014/07/quicksort-algorithm-in-java-in-place-example.html.
 *
 * @author Javin Paul
 */
public class BasicQuickSort {

  /**
   * public method exposed to client, sorts given array using QuickSort
   * Algorithm in Java
   * @param array
   */
  public static void basicQuickSort(int[] array) {
    recursiveQuickSort(array, 0, array.length - 1);
  }

  /**
   * Recursive quicksort logic
   *
   * @param array input array
   * @param startIdx start index of the array
   * @param endIdx end index of the array
   */
  public static void recursiveQuickSort(int[] array, int startIdx,
                                        int endIdx) {

    // modified to account for arrays of size 1 or 2
    if (endIdx - startIdx <= 1) {
      if (array[startIdx] > array[endIdx]) {
        int temp = array[endIdx];
        array[endIdx] = array[startIdx];
        array[startIdx] = temp;
      }
      return;
    }

    int idx = partition(array, startIdx, endIdx);

    // Recursively call quicksort with left part of the partitioned array
    if (startIdx < idx - 1) {
      recursiveQuickSort(array, startIdx, idx - 1);
    }

    // Recursively call quicksort with right part of the partitioned array
    if (endIdx > idx) {
      recursiveQuickSort(array, idx, endIdx);
    }
  }

  /**
   * Divides array from pivot, left side contains elements less than
   * Pivot while right side contains elements greater than pivot.
   *
   * @param array array to partitioned
   * @param left lower bound of the array
   * @param right upper bound of the array
   * @return the partition index
   */
  public static int partition(int[] array, int left, int right) {
    int pivot = array[left]; // taking first element as pivot

    while (left <= right) {
      //searching number which is greater than pivot, bottom up
      while (array[left] < pivot) {
        left++;
      }
      //searching number which is less than pivot, top down
      while (array[right] > pivot) {
        right--;
      }

      // swap the values
      if (left <= right) {
        int tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;

        //increment left index and decrement right index
        left++;
        right--;
      }
    }
    return left;
  }
}
