package com.sk.sorts.utils;

import java.io.File;
import java.util.Arrays;  //TODO Delete
import java.util.Scanner;

import static com.sk.sorts.sortingAlgorithms.quickSorts.BasicQuickSort.basicQuickSort;
import static com.sk.sorts.utils.ioUtils.*;

public class Runs {

  public static void processSorts(File runFile, int numberOfRuns, File inProcessFile, String outputDir, Scanner scanner) {
    FileName fileName = parseFileName(inProcessFile.getName());
    int arraySize = fileName.getFileSize();
    System.out.println("PROCESSING: " + fileName);
    writeFileLineByLine(runFile, "PROCESSING: " + fileName.toString());

    int[] origArray = new int[arraySize];
    int elemCount = 0;
    while(scanner.hasNextLine()) {
      String line = scanner.nextLine();
      int lineInt = Integer.parseInt(line);
      origArray[elemCount] = lineInt;

      elemCount++;
    }


    System.out.println(Arrays.toString(origArray));
    File sortedOutputFile = new File(outputDir + "out_" + fileName.getOriginalFileName());

    if (arraySize == 50) {
      writeFileLineByLine(sortedOutputFile, "INPUT: "+ fileName.toString());
      writeArray(sortedOutputFile, origArray, arraySize);
    }

    // Quicksort with first item as pivot and partitions of size one and two as stopping cases
    basicQuickSortRuns(runFile, sortedOutputFile, numberOfRuns, origArray, arraySize);


    // Quicksort with first item as pivot, 100 as stopping case and insert sort to finish

  }


  public static void basicQuickSortRuns(File runFile, File sortedOutputFile, int numberOfRuns, int[] origArray, int arraySize) {
    System.out.println("\nALGORITHM TYPE: Quicksort with first item as pivot and partitions of size one and two as stopping cases. \n");
    writeFileLineByLine(sortedOutputFile, "\nALGORITHM TYPE: Quicksort with first item as pivot and partitions of size one and two as stopping cases. \n");

    int runCount = 0;
    long beginBasicQuickSort = System.nanoTime();
    boolean isPrinted = false;
    while (runCount < numberOfRuns) {
      int[] arrayCopy = deepCopyArray(origArray, arraySize);
      basicQuickSort(arrayCopy);
      if (!isPrinted) {
        // If array size is 50 then print the sorted array to an output file
        if (arraySize == 50 && !isPrinted) {
          writeFileLineByLine(sortedOutputFile, "SORTED OUTPUT: ");
          writeArray(sortedOutputFile, arrayCopy, arraySize);
          System.out.println("SORTED OUTPUT:" + Arrays.toString(arrayCopy));
          isPrinted = true;
        }
      }
      runCount++;
    }
    long endBasicQuickSort = System.nanoTime();
    long totalTimeBasicQuickSort = (endBasicQuickSort - beginBasicQuickSort)/numberOfRuns;



    System.out.println("TOTAL BASIC SORT RUN TIME: " + totalTimeBasicQuickSort);
    writeFileLineByLine(runFile, " Run time of quicksort with first item as pivot and partitions of size one and two as stopping cases is [" + totalTimeBasicQuickSort + "]. \n");
  }


}
