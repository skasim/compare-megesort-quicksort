package com.sk.sorts.utils;

import com.sk.sorts.sortingAlgorithms.naturalMergeSort.NaturalLinkedMerge;
import com.sk.sorts.sortingAlgorithms.naturalMergeSort.Node;

import java.io.File;
import java.util.Scanner;

import static com.sk.sorts.sortingAlgorithms.naturalMergeSort.NaturalLinkedMerge.naturalMergeSort;
import static com.sk.sorts.sortingAlgorithms.quickSorts.BasicQuickSort.basicQuickSort;
import static com.sk.sorts.sortingAlgorithms.quickSorts.MedianOf3QuickSort.medianOf3QuickSort;
import static com.sk.sorts.sortingAlgorithms.quickSorts.OptimizedQuickSort.optimizedQuickSort;
import static com.sk.sorts.utils.ioUtils.*;

/**
 * Class executes the different runs made in testing the algorithms and the measurement of the algorithm run time.
 * The output is then written to the three different output files. Each of the algorithms is run a certain number of times
 * based on the input provided by the user at program execution time. The average of the runs is then written to output to
 * provide the best possible run time analysis. All the algorithms in this program are recursive.
 *
 * @author Samra Kasim
 */
public class Runs {
  /**
   * Method is called by the main class. It is where all the five different types of algorithms (4 quicksorts and 1 natural
   * merge sort) are run. If a file is of size 50 then it's output is also written. Otherwise, output is wrtten to a .txt file
   * and a .csv file. The .txt file is a human readable file, while the .csv file is designed to be loaded into excel for analysis.
   * @param runFile: File object representing the file to which the run times will be written to in human readable format.
   * @param numberOfRuns: int value for the number of runs per algorithm.
   * @param inProcessFile: File object representing the input file that is being processed.
   * @param outputDir: String value representing the directory where the output files will be stored.
   * @param scanner: Scanner object to read the input files.
   * @param csvFile: File object representing the csv file to which output is written.
   */
  public static void processSorts(File runFile, int numberOfRuns, File inProcessFile, String outputDir, Scanner scanner, File csvFile) throws NotValidInputException {
    // Parse the filename into the fileName object to be able to get the fileSize and input file sorting order
    FileName fileName = parseFileName(inProcessFile.getName());
    int arraySize = fileName.getFileSize();
    writeFileLineByLine(runFile, fileName.toString());

    // Instantiate a new array based on the size of the input file
    int[] origArray = new int[arraySize];
    int elemCount = 0;

    // Read in the input file
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      try {
        int lineInt = Integer.parseInt(line);
        origArray[elemCount] = lineInt;

        elemCount++;
      } catch (NumberFormatException e) {
        System.err.println("Invalid input. " + e);
      }
    }

    // Instantiate an output file
    File sortedOutputFile = new File(outputDir + "out_" + fileName.getOriginalFileName());
    // Write to output file only if size of the input file is 50
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
    basicQuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize, csvFile, fileName);

    // Quicksort with first item as pivot, 100 as stopping case, and insertion sort to finish
    optimizedQuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize, 100, csvFile, fileName);

    // Quicksort with first item as pivot, 50 ass stopping case, and insertion sort to finish
    optimizedQuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize, 50, csvFile, fileName);

    // Quicksort with median-of-three as pivot and partitions of size one and two as stopping cases
    medianOf3QuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize, csvFile, fileName);

    // Natural merge sort with linked list
    naturalLinkedMergeSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize, csvFile, fileName);
  }

  /**
   * A Basic Quicksort that has partitions of size one and two as stopping cases. The algorithm runs a particular number of times
   * based on user input and then averages the run time. The output is then written to the various output files.
   * @param runFile: File object representing the file to which the run times will be written to in human readable format.
   * @param sortedOutputFile: File object representing the output .dat file that the sorted array is written to.
   * @param numberOfRuns: int value for the number of runs per algorithm.
   * @param origArray: int[] representing the original array derived from input.
   * @param arraySize: size of the input array.
   * @param csvFile: File object representing the csv file to which output is written.
   * @param fileName: FileName object representing the input file.
   */
  private static void basicQuickSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize, File csvFile, FileName fileName) {
    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Algorithm type applied: Quicksort with first item as pivot and partitions of size one and two as stopping cases.");
    }

    int runCount = 0;
    long totalTime = 0;
    boolean isPrinted = false;

    while (runCount < numberOfRuns) {
      // original array is deep copied for each new run
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      // The algorithm is timed only during it's run
      long begin = System.nanoTime();
      basicQuickSort(arrayCopy);
      long end = System.nanoTime();
      if (arraySize == 50 && !isPrinted) {
        // If array size is 50 then print the sorted array to an output file
        writeFileLineByLine(sortedOutputFile, "Sorted output: ");
        writeArray(sortedOutputFile, arrayCopy, arraySize);
        isPrinted = true;
      }
      runCount++;
      // Run time is summed
      totalTime = totalTime + (end - begin);
    }
    // Run time is averaged
    long totalTimeQuickSort = (totalTime)/numberOfRuns;

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Total sort run time: " + totalTimeQuickSort + " ns \n");
    }
    writeFileLineByLine(runFile, "  Run time of quicksort with first item as pivot and partitions of size one and two as stopping cases is [" + totalTimeQuickSort + " ns]. \n");
    writeFileLineByLine(csvFile, fileName.getFileName() + "," + fileName.getFileSize() + "," + totalTimeQuickSort + "," + "basic quick sort");
  }

  /**
   * An optimized quicksort that runs a basic quicksort until the stopping case is reached at which point it switches to an insertion sort.
   * @param runFile: File object representing the file to which the run times will be written to in human readable format.
   * @param sortedOutputFile: File object representing the output .dat file that the sorted array is written to.
   * @param numberOfRuns: int value for the number of runs per algorithm.
   * @param origArray: int[] representing the original array derived from input.
   * @param arraySize: size of the input array.
   * @param stoppingCase: int value representing the stopping case.
   * @param csvFile: File object representing the csv file to which output is written.
   * @param fileName: FileName object representing the input file.
   */
  private static void optimizedQuickSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize, int stoppingCase, File csvFile, FileName fileName) {
    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Algorithm type applied: Quicksort with first item as pivot, " + stoppingCase + " as stopping case, and insertion sort to finish.");
    }

    int runCount = 0;
    long totalTime = 0;
    boolean isPrinted = false;

    while (runCount < numberOfRuns) {
      // original array is deep copied for each new run
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      long begin = System.nanoTime();
      // stoppingCase is passed to the algorithm
      optimizedQuickSort(arrayCopy, stoppingCase);
      long end = System.nanoTime();
      if ( arraySize == 50 && !isPrinted) {
        // If array size is 50 then print the sorted array to an output file
        writeFileLineByLine(sortedOutputFile, "Sorted output: ");
        writeArray(sortedOutputFile, arrayCopy, arraySize);
        isPrinted = true;

      }
      runCount++;
      totalTime = totalTime + (end - begin);
    }
    // Run time is averaged
    long totalTimeQuickSort = (totalTime)/numberOfRuns;

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Total sort run time: " + totalTimeQuickSort + " ns \n");
    }
    writeFileLineByLine(runFile, "  Run time of quicksort with first item as pivot, " + stoppingCase + " as stopping case, and insertion sort to finish is [" + totalTimeQuickSort + " ns]. \n");
    writeFileLineByLine(csvFile, fileName.getFileName() + "," + fileName.getFileSize() + "," + totalTimeQuickSort + "," + "insertion quick sort ("+stoppingCase +")");
  }

  /**
   * Similar to the basic quick sort but uses a median-of-three as a partition and then size one and two as stopping cases.
   * @param runFile: File object representing the file to which the run times will be written to in human readable format.
   * @param sortedOutputFile: File object representing the output .dat file that the sorted array is written to.
   * @param numberOfRuns: int value for the number of runs per algorithm.
   * @param origArray: int[] representing the original array derived from input.
   * @param arraySize: size of the input array.
   * @param csvFile: File object representing the csv file to which output is written.
   * @param fileName: FileName object representing the input file.
   */
  private static void medianOf3QuickSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize, File csvFile, FileName fileName) {
    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Algorithm type applied: Quicksort with median-of-three as pivot and partitions of size one and two as stopping cases.");
    }

    int runCount = 0;
    long totalTime = 0;
    boolean isPrinted = false;

    while (runCount < 1) {
      // original array is deep copied for each new run
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      long begin = System.nanoTime();
      medianOf3QuickSort(arrayCopy);
      long end = System.nanoTime();
      if (arraySize == 50 && !isPrinted) {
        // If array size is 50 then print the sorted array to an output file
        writeFileLineByLine(sortedOutputFile, "Sorted output: ");
        writeArray(sortedOutputFile, arrayCopy, arraySize);
        isPrinted = true;
      }
      runCount++;
      totalTime = totalTime + (end - begin);
    }
    // Averaging of run time
    long totalTimeQuickSort = (totalTime)/numberOfRuns;

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Total sort run time: " + totalTimeQuickSort + " ns \n");
    }
    writeFileLineByLine(runFile, "  Run time of quicksort with median of three as pivot and partitions of size one and two as stopping cases is [" + totalTimeQuickSort + " ns]. \n");
    writeFileLineByLine(csvFile, fileName.getFileName() + "," + fileName.getFileSize() + "," + totalTimeQuickSort + "," + "median of three quick sort");
  }

  /**
   * A natural merge sort that uses a linked list representing of the array.
   * @param runFile: File object representing the file to which the run times will be written to in human readable format.
   * @param sortedOutputFile: File object representing the output .dat file that the sorted array is written to.
   * @param numberOfRuns: int value for the number of runs per algorithm.
   * @param origArray: int[] representing the original array derived from input.
   * @param arraySize: size of the input array.
   * @param csvFile: File object representing the csv file to which output is written.
   * @param fileName: FileName object representing the input file.
   */
  private static void naturalLinkedMergeSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize, File csvFile, FileName fileName) {
    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Algorithm type applied: Natural Merge Sort using a linked list.");
    }

    int runCount = 0;
    long totalTime = 0;
    boolean isPrinted = false;

    while (runCount < numberOfRuns) {
      // original array is deep copied for each new run
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      // The NaturalLinkedMerge object is instantiated and the linked list is created, but it is not included in the run time
      // since array creation was not included in run time for the other objects
      // However, the creation of runs is included in the run time
      NaturalLinkedMerge linkedMerge = new NaturalLinkedMerge();
      for (int i=0; i<arraySize; i++) {
        linkedMerge.addNode(arrayCopy[i]);
      }
      long begin = System.nanoTime();
      Node sortedList = naturalMergeSort(linkedMerge);
      long end = System.nanoTime();
      int[] sortedArray = linkedMerge.printLinkList(sortedList, arraySize);

      // If array size is 50 then print the sorted array to an output file
      if (arraySize == 50 && !isPrinted) {
        writeFileLineByLine(sortedOutputFile, "Sorted output: ");
        writeArray(sortedOutputFile, sortedArray, arraySize);
        isPrinted = true;
      }
      runCount++;
      totalTime = totalTime + (end - begin);
    }
    // Averaging run time
    long totalTimeNaturalSort = (totalTime) / numberOfRuns;

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "Total sort run time: " + totalTimeNaturalSort + " ns\n");
    }
    writeFileLineByLine(runFile, "  Run time of natural merge sort using a linked list is [" + totalTimeNaturalSort + " ns]. \n");
    writeFileLineByLine(csvFile, fileName.getFileName() + "," + fileName.getFileSize() + "," + totalTimeNaturalSort + "," + "natural merge sort");

  }

}
