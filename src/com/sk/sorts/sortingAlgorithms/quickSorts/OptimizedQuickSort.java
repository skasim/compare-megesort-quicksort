package com.sk.sorts.sortingAlgorithms.quickSorts;

/**
 * The class uses recursive methods from https://www.java67.com/2014/07/quicksort-algorithm-in-java-in-place-example.html
 * and the use of insertion sort from https://www.techiedelight.com/hybrid-quicksort/
 *
 * @author Javin Paul and unknown
 */
public class OptimizedQuickSort {
  // TODO: from https://www.java67.com/2014/07/quicksort-algorithm-in-java-in-place-example.html
  // TODO: from https://www.techiedelight.com/hybrid-quicksort/

  /**
   * public method exposed to client, sorts given array using QuickSort
   * Algorithm in Java
   * @param array
   */
  public static void optimizedQuickSort(int[] array, int stoppingCase) {
    recursiveQuickSortWithInsertionSort(array, 0, array.length - 1, stoppingCase);
  }

  /**
   * Recursive quicksort logic
   *
   * @param array input array
   * @param startIdx start index of the array
   * @param endIdx end index of the array
   */
  public static void recursiveQuickSortWithInsertionSort(int[] array, int startIdx,
                                        int endIdx, int stoppingCase) {

    int idx = partition(array, startIdx, endIdx);

    if (endIdx - startIdx <= stoppingCase) {
      insertionSort(array, startIdx, array.length-1);
      return;
    }


    // Recursively call quicksort with left part of the partitioned array
    recursiveQuickSortWithInsertionSort(array, startIdx, idx - 1, stoppingCase);

    // Recursively call quicksort with right part of the partitioned array
    recursiveQuickSortWithInsertionSort(array, idx, endIdx, stoppingCase);
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

  // perform insertion sort on arr[]
  public static void insertionSort(int[] arr, int start, int n)
  {
    // Start from second element (element at index 0
    // is already sorted)
    for (int i = start + 1; i <= n; i++)
    {
      int value = arr[i];
      int j = i;

      // Find the index j within the sorted subset arr[0..i-1]
      // where element arr[i] belongs
      while (j > start && arr[j - 1] > value)
      {
        arr[j] = arr[j - 1];
        j--;
      }
      // Note that subarray arr[j..i-1] is shifted to
      // the right by one position i.e. arr[j+1..i]
      arr[j] = value;
    }
  }

}
