package com.sk.sorts.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ioUtils {

  public static int[] deepCopyArray(int[] originalArray, int fileSize) {
    int[] deepCopy = new int[fileSize];

    for (int i=0; i<fileSize; i++) {
      deepCopy[i] = originalArray[i];
    }
    return deepCopy;
  }

  public static FileName parseFileName(String fileName) {
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
   * Helper method to facilitate the writing of an com.sk.paths.array to output. The method first loops through the com.sk.paths.array and
   * then prints out the com.sk.paths.array integer by integer
   * @param outFile: File object representing the output file.
   * @param arr: int[] object representing the path.
   * @param pathSize: int object representing the size of the path to mitigate use of .length or .size functions.
   */
  public static void writeArray(File outFile, int[] arr, int arraySize)  {
    BufferedWriter writer = createWriter(outFile);
    try {
      writer.newLine();
      // Loops through an array to print it out as integers instead of using a .toString method
      for (int i=0; i<arraySize; i++) {
        writer.write(arr[i] + " ");
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
}
