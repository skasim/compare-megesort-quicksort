package com.sk.sorts.sortingAlgorithms.quickSortExamples;

import java.util.Arrays;
import java.util.Random;

class QuickSortMultiTry
{
  // Number of elements to be sorted
  private static final int N = 10000;

  // Number of sorting runs
  private static final int NUM = 10;

  // perform insertion sort on arr[]
  public static void insertionSort(int[] arr, int low, int n)
  {
    // Start from second element (element at index 0
    // is already sorted)
    for (int i = low + 1; i <= n; i++)
    {
      int value = arr[i];
      int j = i;

      // Find the index j within the sorted subset arr[0..i-1]
      // where element arr[i] belongs
      while (j > low && arr[j - 1] > value)
      {
        arr[j] = arr[j - 1];
        j--;
      }
      // Note that subarray arr[j..i-1] is shifted to
      // the right by one position i.e. arr[j+1..i]

      arr[j] = value;
    }
  }

  public static int Partition (int[] a, int low, int high)
  {
    // Pick rightmost element as pivot from the array
    int pivot = a[high];

    // elements less than pivot will be pushed to the left of pIndex
    // elements more than pivot will be pushed to the right of pIndex
    // equal elements can go either way
    int pIndex = low;

    // each time we finds an element less than or equal to pivot,
    // pIndex is incremented and that element would be placed
    // before the pivot.
    for (int i = low; i < high; i++)
    {
      if (a[i] <= pivot)
      {
        int temp = a[i];
        a[i] = a[pIndex];
        a[pIndex] = temp;

        pIndex++;
      }
    }
    // swap pIndex with Pivot
    int temp = a[high];
    a[high] = a[pIndex];
    a[pIndex] = temp;

    // return pIndex (index of pivot element)
    return pIndex;
  }

  public static void QuickSort(int[] a, int low, int high)
  {
    // base condition
    if(low >= high)
      return;

    // rearrange the elements across pivot
    int pivot = Partition(a, low, high);

    // recur on sub-array containing elements less than pivot
    QuickSort(a, low, pivot - 1);

    // recur on sub-array containing elements more than pivot
    QuickSort(a, pivot + 1, high);
  }

  public static void optimizedQuickSort(int[] A, int low, int high)
  {
    while (low < high)
    {
      // do insertion sort if 10 or smaller
      if(high - low < 10)
      {
        insertionSort(A, low, high);
        break;
      }
      else
      {
        int pivot = Partition(A, low, high);

        // tail call optimizations - recur on smaller sub-array
        if (pivot - low < high - pivot) {
          optimizedQuickSort(A, low, pivot - 1);
          low = pivot + 1;
        } else {
          optimizedQuickSort(A, pivot + 1, high);
          high = pivot - 1;
        }
      }
    }
  }

  public static void main(String[] args)
  {
    int arr[] = new int[N];

    // to measure time taken by optimized and non-optimized Quicksort
    long begin, end;
    long t1 = 0, t2 = 0;

    // perform Quicksort NUM times and take average
    for(int i = 0; i < NUM; i++)
    {
      // generate random input
      Arrays.fill(arr, new Random().nextInt());
      int dup[] = Arrays.copyOf(arr, N);

      // Perform non-optimized Quicksort on arr

      begin = System.nanoTime();
      QuickSort(arr, 0, N-1);
      end = System.nanoTime();

      // calculate time taken by Non-Optimized QuickSortMultiTry
      t1 += (end - begin);

      // Perform Optimized Quicksort on dup[]
      begin = System.nanoTime();
      optimizedQuickSort(dup, 0, N-1);
      end = System.nanoTime();

      // calculate time taken by optimized QuickSortMultiTry
      t2 += (end - begin);
    }

    System.out.println("Average time taken by Non-Optimized quicksort: "
          + t1/NUM + "ns");

    System.out.println("Average time taken by Optimized quicksort: "
          + t2/NUM + "ns");
  }
}