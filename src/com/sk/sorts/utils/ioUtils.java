package com.sk.sorts.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Helper class that facilitates reading and processing of input files and writing to output files.
 *
 * @author Samra Kasim
 */
public class ioUtils {
  /**
   * Method designed to deep copy an array, i.e., create a new array instead of passing it by reference. Since, the program
   * design tests each algorith multiple times but only reads the input file, this method ensure that a new array is created
   * and on this array the algorithms are applied. This way, the original array stays intact and can be copied again for
   * algorithm application.
   * @param originalArray: int[] representing the original array
   * @param fileSize: int value representing the fileSize
   * @return int[] representing a new array.
   */
  public static int[] deepCopyArray(int[] originalArray, int fileSize) {
    int[] deepCopy = new int[fileSize];

    for (int i=0; i<fileSize; i++) {
      deepCopy[i] = originalArray[i];
    }
    return deepCopy;
  }

  /**
   * Method to parse the filename, which is the format asc1k.dat into a FileName object, which is then utilized by application
   * for determining loop counts and for writing to output.
   * @param fileName: String value representing the file name
   * @return FileName object
   */
  public static FileName parseFileName(String fileName) throws NotValidInputException {
    int count = 0;
    String name = "";
    String fileSize = "";
    for (char c : fileName.toCharArray()) {
      if (count >= 0 && count <=2) {
        name += c;
      }
      if (count == 3 || count == 4 || count == 5) {
        if (!String.valueOf(c).equals(".")) {
          fileSize += c;
        }
      }
      count ++;
    }
    FileName fn = new FileName(name, fileSize);
    return fn;
  }

  /**
   * Helper method to take a File object representing the output file and return a BufferedWrite object.
   * @param outFile: String value representing the output file
   * @return a BufferedWriter object
   */
  private static BufferedWriter createWriter(File outFile) {
    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(outFile, true));
    } catch (IOException e) {
      System.err.println(e.toString());
    }
    return writer;
  }

  /**
   * Helper method to facilitate the writing of a file line by line (by appending to a file).
   * @param outFile: File object representing the output file.
   * @param line: String value representing a line of input text.
   */
  public static void writeFileLineByLine(File outFile, String line)  {
    BufferedWriter writer = createWriter(outFile);
    try {
      writer.newLine();
      if (line != null) {
        writer.write(line);
      }
    } catch (IOException e) {
      System.err.println(e.toString());
    }
    try {
      writer.close();
    } catch (IOException e) {
      System.err.println(e.toString());
    }
  }

  /**
   * Helper method to create a PrintWriter.
   * @param outFile: File object representing the output file.
   * @return PrinteWriter object
   */
  private static PrintWriter createPrintWriter(File outFile) {
    FileWriter fileWriter = null;
    try {
      fileWriter = new FileWriter(outFile);
    } catch (IOException e) {
      System.err.println(e.toString());
    }
    return new PrintWriter(fileWriter);
  }

  /**
   * Helper method to facilitate the writing of an array to output. The method first loops through the array and
   * then prints out the array integer by integer. Does not print a newline at the end of the file.
   * @param outfile: File object representing the output file.
   * @param arr: int[] object representing the path.
   * @param arraySize: int object representing the size of the array to mitigate use of .length or .size functions.
   */

  public static void writeArray(File outfile, int[] arr, int arraySize) {
    PrintWriter printWriter = createPrintWriter(outfile);

    for (int i=0; i<arraySize; i++) {
      if (i != arraySize -1) {
        printWriter.print(arr[i] + "\n");
      } else {
        printWriter.print(arr[i]);
      }
    }
    printWriter.close();
  }
}
