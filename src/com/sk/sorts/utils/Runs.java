package com.sk.sorts.utils;

import com.sk.sorts.sortingAlgorithms.naturalMergeSort.NaturalLinkedMerge;
import com.sk.sorts.sortingAlgorithms.naturalMergeSort.Node;

import java.io.File;
import java.util.Scanner;

import static com.sk.sorts.sortingAlgorithms.quickSorts.BasicQuickSort.basicQuickSort;
import static com.sk.sorts.sortingAlgorithms.quickSorts.MedianOf3QuickSort.medianOf3QuickSort;
import static com.sk.sorts.sortingAlgorithms.quickSorts.OptimizedQuickSort.optimizedQuickSort;
import static com.sk.sorts.utils.ioUtils.*;

public class Runs {

  public static void processSorts(File runFile, int numberOfRuns, File inProcessFile, String outputDir, Scanner scanner) {
    FileName fileName = parseFileName(inProcessFile.getName());
    int arraySize = fileName.getFileSize();
    writeFileLineByLine(runFile, "Now processing: " + fileName.toString());

    int[] origArray = new int[arraySize];
    int elemCount = 0;
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      int lineInt = Integer.parseInt(line);
      origArray[elemCount] = lineInt;

      elemCount++;
    }


    File sortedOutputFile = new File(outputDir + "out_" + fileName.getOriginalFileName());

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "##############################################################################\n");
      writeFileLineByLine(sortedOutputFile, "Sorting " + fileName.toString());
      writeFileLineByLine(sortedOutputFile, "By: Samra Kasim");
      writeFileLineByLine(sortedOutputFile, "###############################################################################\n");
      writeFileLineByLine(sortedOutputFile, "Input: ");
      writeArray(sortedOutputFile, origArray, arraySize);
      writeFileLineByLine(sortedOutputFile, " ");
    }

    // Quicksort with first item as pivot and partitions of size one and two as stopping cases
    basicQuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize);

    // Quicksort with first item as pivot, 100 as stopping case, and insertion sort to finish
    optimizedQuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize, 100);

    // Quicksort with first item as pivot, 50 ass stopping case, and insertion sort to finish
    optimizedQuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize, 50);

    // Quicksort with median-of-three as pivot and partitions of size one and two as stopping cases
    medianOf3QuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize);

    // Natural merge sort with linked list
    naturalLinkedMergeSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize);
  }


  private static void basicQuickSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize) {
    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Algorithm type applied: Quicksort with first item as pivot and partitions of size one and two as stopping cases.");
    }

    int runCount = 0;
    long beginQuickSort = System.nanoTime();
    boolean isPrinted = false;
    while (runCount < numberOfRuns) {
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      basicQuickSort(arrayCopy);
      if (!isPrinted) {
        // If array size is 50 then print the sorted array to an output file
        if (arraySize == 50 && !isPrinted) {
          writeFileLineByLine(sortedOutputFile, "Sorted output: ");
          writeArray(sortedOutputFile, arrayCopy, arraySize);
          isPrinted = true;
        }
      }
      runCount++;
    }
    long endQuickSort = System.nanoTime();
    long totalTimeQuickSort = (endQuickSort - beginQuickSort)/numberOfRuns;

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Total sort run time: " + totalTimeQuickSort + "\n");
    }
    writeFileLineByLine(runFile, "  Run time of quicksort with first item as pivot and partitions of size one and two as stopping cases is [" + totalTimeQuickSort + " ns]. \n");
  }

  private static void optimizedQuickSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize, int stoppingCase) {
    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Algorithm type applied: Quicksort with first item as pivot, " + stoppingCase + " as stopping case, and insertion sort to finish.");
    }

    int runCount = 0;
    long beginQuickSort = System.nanoTime();
    boolean isPrinted = false;
    while (runCount < numberOfRuns) {
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      optimizedQuickSort(arrayCopy, stoppingCase);
      if (!isPrinted) {
        // If array size is 50 then print the sorted array to an output file
        if (arraySize == 50 && !isPrinted) {
          writeFileLineByLine(sortedOutputFile, "Sorted output: ");
          writeArray(sortedOutputFile, arrayCopy, arraySize);
          isPrinted = true;
        }
      }
      runCount++;
    }
    long endQuickSort = System.nanoTime();
    long totalTimeQuickSort = (endQuickSort - beginQuickSort)/numberOfRuns;

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Total sort run time: " + totalTimeQuickSort + "\n");
    }
    writeFileLineByLine(runFile, "  Run time of quicksort with first item as pivot, " + stoppingCase + " as stopping case, and insertion sort to finish is [" + totalTimeQuickSort + " ns]. \n");
  }

  private static void medianOf3QuickSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize) {
    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Algorithm type applied: Quicksort with median-of-three as pivot and partitions of size one and two as stopping cases.");
    }

    int runCount = 0;
    long beginQuickSort = System.nanoTime();
    boolean isPrinted = false;
    while (runCount < 1) {
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      medianOf3QuickSort(arrayCopy);
      if (!isPrinted) {
        // If array size is 50 then print the sorted array to an output file
        if (arraySize == 50 && !isPrinted) {
          writeFileLineByLine(sortedOutputFile, "Sorted output: ");
          writeArray(sortedOutputFile, arrayCopy, arraySize);
          isPrinted = true;
        }
      }
      runCount++;
    }
    long endQuickSort = System.nanoTime();
    long totalTimeQuickSort = (endQuickSort - beginQuickSort)/numberOfRuns;

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Total sort run time: " + totalTimeQuickSort + "\n");
    }
    writeFileLineByLine(runFile, "  Run time of quicksort with median of three as pivot and partitions of size one and two as stopping cases is [" + totalTimeQuickSort + " ns]. \n");
  }

  public static void naturalLinkedMergeSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize) {
    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Algorithm type applied: Natural Merge Sort using a linked list.");
    }

    int runCount = 0;
    long beginNaturalSort = System.nanoTime();
    boolean isPrinted = false;

    while (runCount < numberOfRuns) {
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      NaturalLinkedMerge linkedMerge = new NaturalLinkedMerge();
      for (int i=0; i<arraySize; i++) {
        linkedMerge.addNode(arrayCopy[i]);
      }
      linkedMerge.createPointersLinkedList();
      Node sortedList = linkedMerge.mergeSort();
      int[] sortedArray = linkedMerge.printLinkList(sortedList, arraySize);

      // If array size is 50 then print the sorted array to an output file
      if (arraySize == 50 && !isPrinted) {
        writeFileLineByLine(sortedOutputFile, "Sorted output: ");
        writeArray(sortedOutputFile, sortedArray, arraySize);
        isPrinted = true;
      }
      runCount++;
    }
    long endNaturalSort = System.nanoTime();
    long totalTimeNaturalSort = (endNaturalSort - beginNaturalSort) / numberOfRuns;

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Total sort run time: " + totalTimeNaturalSort + "\n");
    }
    writeFileLineByLine(runFile, "  Run time of natural merge sort using a linked list is [" + totalTimeNaturalSort + " ns]. \n");

  }

}
