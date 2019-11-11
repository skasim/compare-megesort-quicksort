package com.sk.sorts.sortingAlgorithms;

import java.util.Arrays;

public class MedianOf3QuickSort {
  // TODO https://examples.javacodegeeks.com/core-java/quicksort-algorithm-in-java-code-example/

  public static void main(String args[]) {

    int[] input = { 7, 2, 1, 6, 8, 5, 3, 4};
    System.out.println("Before sorting : " + Arrays.toString(input));
    medianOf3QuickSort(input); // sort the integer array using quick sort algorithm
    System.out.println("After sorting : " + Arrays.toString(input));


    int[] input2 = { 23, 31, 1, 21, 36, 72};
    System.out.println("Before sorting : " + Arrays.toString(input2));
    medianOf3QuickSort(input2); // sort the integer array using quick sort algorithm
    System.out.println("After sorting : " + Arrays.toString(input2));

    // input with duplicates
    int[] withDuplicates = { 11, 14, 16, 12, 11, 15};
    System.out.println("Before sorting : " + Arrays.toString(withDuplicates));
    medianOf3QuickSort(withDuplicates); // sort the array using quick sort algorithm
    System.out.println("After sorting : " + Arrays.toString(withDuplicates));
  }

  /**
   * public method exposed to client, sorts given array using QuickSortMultiTry //TODO FIX
   * Algorithm in Java
   * @param array
   */
  public static void medianOf3QuickSort(int[] array) {
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

    if (endIdx - startIdx <= 1) {
      return;
    }

    if (endIdx - startIdx == 2) {
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
  }
  public static int getMedian(int[] a, int left,int right){
    int center = (left+right)/2;

    if(a[left] > a[center])
      swap(a, left,center);

    if(a[left] > a[right])
      swap(a, left, right);

    if(a[center] > a[right])
      swap(a, center, right);

    swap(a, center, right);
    return a[right];
  }

  // This method is used to swap the values between the two given index
  public static void swap(int[] a, int left,int right){
    int temp = a[left];
    a[left] = a[right];
    a[right] = temp;
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
    int pivot = getMedian(array, left, right); // taking first element as pivot

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
