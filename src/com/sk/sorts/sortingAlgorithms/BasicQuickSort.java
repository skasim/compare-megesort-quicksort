package com.sk.sorts.sortingAlgorithms;

import java.util.Arrays;


/**
 * Java program to Sort integer array using QuickSortMultiTry algorithm using recursion.
 * Recursive QuickSortMultiTry algorithm, partitioned list into two parts by a pivot,
 * and then recursively sorts each list.
 * @author Javin Paul
 */
public class BasicQuickSort {

  public static void main(String args[]) {

    int[] input = { 7, 2, 1, 6, 8, 5, 3, 4};
    System.out.println("Before sorting : " + Arrays.toString(input));
    basicQuickSort(input); // sort the integer array using quick sort algorithm
    System.out.println("After sorting : " + Arrays.toString(input));


    int[] input2 = { 23, 31, 1, 21, 36, 72};
    System.out.println("Before sorting : " + Arrays.toString(input2));
    basicQuickSort(input2); // sort the integer array using quick sort algorithm
    System.out.println("After sorting : " + Arrays.toString(input2));

    // input with duplicates
    int[] withDuplicates = { 11, 14, 16, 12, 11, 15};
    System.out.println("Before sorting : " + Arrays.toString(withDuplicates));
    basicQuickSort(withDuplicates); // sort the array using quick sort algorithm
    System.out.println("After sorting : " + Arrays.toString(withDuplicates));
  }

  /**
   * public method exposed to client, sorts given array using QuickSortMultiTry
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

    int idx = partition(array, startIdx, endIdx);
    System.out.println("start index: " + startIdx);
    System.out.println("end index: " + endIdx);

    if (endIdx - startIdx <= 1) {
      System.out.println("1 index");
      return;
    }

    if (endIdx - startIdx == 2) {
      System.out.println("2 index");
      if (array[startIdx] > array[endIdx]) {
        int temp = array[endIdx];
        array[endIdx] = array[startIdx];
        array[startIdx] = temp;
      }
    }

    // Recursively call quicksort with left part of the partitioned array
    recursiveQuickSort(array, startIdx, idx - 1);

    // Recursively call quicksort with right part of the partitioned array
    recursiveQuickSort(array, idx, endIdx);


//    // Recursively call quicksort with left part of the partitioned array
//    if (startIdx < idx - 1) {
//      recursiveQuickSort(array, startIdx, idx - 1);
//    }
//
//    // Recursively call quick sort with right part of the partitioned array
//    if (endIdx > idx) {
//      recursiveQuickSort(array, idx, endIdx);
//    }
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
