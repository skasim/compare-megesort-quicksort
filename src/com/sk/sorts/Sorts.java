package com.sk.sorts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.sk.sorts.utils.Runs.processSorts;
import static com.sk.sorts.utils.ioUtils.*;

public class Sorts {

  public static void main(String[] args) {
    // Check if input and output directories and run filename are provided. If not, exit program execution.
    if (args.length != 3) {
      System.err.println("Input and output file paths must be provided to run this simulation. Exiting now.");
      System.exit(1);
    }
    // Variables related to program IO
    String inputDir = args[0];
    String outputDir = args[1];
    String runFilename = args[2];
    String csvRunFilename = runFilename + ".csv";
    File folder = new File(inputDir);
    File[] files = folder.listFiles();

    int numberOfRuns = 3;
    File runFile = new File(outputDir + runFilename);
    File csvFile = new File(outputDir + csvRunFilename);
    writeFileLineByLine(runFile, "##################################################\n");
    writeFileLineByLine(runFile, "    Lab4: Sorting Algorithms Run Time Analysis    \n");
    writeFileLineByLine(runFile, "          Using Recursive Sorts with " + numberOfRuns+"\n");
    writeFileLineByLine(runFile, "    By: Samra Kasim                                \n");
    writeFileLineByLine(runFile, "##################################################\n");

    writeFileLineByLine(csvFile, "Run Time CSV File By Samra Kasim");
    writeFileLineByLine(csvFile, "order;size;time");

    System.out.println("Beginning processing...");
    for (File file : files) {
      if (file.isFile() && !file.getName().equals(".DS_Store")) {
        try {
          System.out.println("  "+ file.getName() +"...");
          Scanner scanner = new Scanner(file);
          processSorts(runFile, numberOfRuns, file, outputDir, scanner, csvFile);
        } catch (FileNotFoundException e) {
          System.err.println("File [" + file.getName() + "] not found.");
        }

      }
    }
    System.out.println("Processing complete.");
  }


}
