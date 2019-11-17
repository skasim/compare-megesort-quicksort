package com.sk.sorts;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.sk.sorts.utils.Runs.processSorts;
import static com.sk.sorts.utils.ioUtils.*;

public class Sorts {

  public static void main(String[] args) {
    // Check if input and output directories are provided. If not, exit program execution.
    if (args.length != 3) {
      System.err.println("Input and output file paths must be provided to run this simulation. Exiting now.");
      System.exit(1);
    }
    // Variables related to program IO
    String inputDir = args[0];
    String outputDir = args[1];
    String runFilename = args[2];
    File folder = new File(inputDir);
    File[] files = folder.listFiles();

    int numberOfRuns = 3;
    File runFile = new File(outputDir + runFilename);
    writeFileLineByLine(runFile, "##################################################\n");
    writeFileLineByLine(runFile, "#    Lab4: Sorting Algorithms Run Time Analysis  #\n");
    writeFileLineByLine(runFile, "#    By: Samra Kasim                             #\n");
    writeFileLineByLine(runFile, "#################################################\n");

    for (File file : files) {
      if (file.isFile() && !file.getName().equals(".DS_Store")) {
        try {
          Scanner scanner = new Scanner(file);
          processSorts(runFile, numberOfRuns, file, outputDir, scanner);
        } catch (FileNotFoundException e) {
          System.err.println("File [" + file.getName() + "] not found.");
        }

      }
    }
  }


}
