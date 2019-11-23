package com.sk.sorts;

import com.sk.sorts.utils.NotValidInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.sk.sorts.utils.Runs.processSorts;
import static com.sk.sorts.utils.ioUtils.*;

/**
 * Program to measure and compare the run times of four types of Quicksort algorithms and a Natural Merge Sort algorithm.
 * All the sorts in this program use recursion. The user provides in the command line the input and output directories,
 * the name of the runFile, which represents the human readable output file and the number of runs that the user wishes to make.
 * The higher the number of runs the more accurate the runtime determination for each algorithm since these run times are averaged.
 * The output files are found in the output directory and three different output files are provided: 1) sorted output files for
 * file size of 50, 2) file that has run time in human readable format. Much of the code for the algorithms was found
 * online. The resources are cited in the javadoc for the specific algorithms.
 *
 * Please use the README for directions on program execution.
 *
 * @author Samra Kasim
 */
public class Sorts {

  public static void main(String[] args) {
    // Check if input and output directories, run filename, and number of runs  are provided. If not, exit program execution.
    if (args.length != 4) {
      System.err.println("Input and output file paths must be provided to run this simulation. Exiting now.");
      System.exit(1);
    }
    // Variables related to program IO
    String inputDir = args[0];
    String outputDir = args[1];
    String runFilename = args[2];
    int numberOfRuns = Integer.parseInt(args[3]);
    if (numberOfRuns < 3) {
      numberOfRuns = 3; // minimum number of runs to get proper run time;
    }
    File folder = new File(inputDir);
    File[] files = folder.listFiles();

    // Create new runFile
    File runFile = new File(outputDir + runFilename);

    // Add some pizazz to the output files
    writeFileLineByLine(runFile, "#######################################################################\n");
    writeFileLineByLine(runFile, "    Lab4: Sorting Algorithms Run Time Analysis    \n");
    writeFileLineByLine(runFile, "          Using Recursive Sorts And Averaging " + numberOfRuns+" Runs Per Algorithm\n");
    writeFileLineByLine(runFile, "    By: Samra Kasim                                \n");
    writeFileLineByLine(runFile, "#######################################################################\n");

    System.out.println("Beginning processing...");
    // Parse through each file in the input director
    for (File file : files) {
      if (file.isFile() && !file.getName().equals(".DS_Store")) {
        try {
          System.out.println("  "+ file.getName() +"...");
          // Instantiate a new scanner object for each file
          Scanner scanner = new Scanner(file);
          // Process each file and run the algorithms on each file
          processSorts(runFile, numberOfRuns, file, outputDir, scanner);
        } catch (FileNotFoundException e) {
          System.err.println("File [" + file.getName() + "] not found. " + e);
        } catch (NotValidInputException e) {
          System.err.println("File[" + file.getName() +"] " + e);
        }

      }
    }
    System.out.println("Processing complete.");
  }


}
