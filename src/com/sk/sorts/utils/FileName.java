package com.sk.sorts.utils;

/**
 * FileName object representing the names of the files provided as input. Provides a helpful way to parse the name and
 * size of the file to be used by methods in this application.
 *
 * @author Samra Kasim
 */
public class FileName {

  private String fileName; // represents the name of the file such as ascending, descending, etc.
  private int fileSize;  // represents the size of the file such as an int such as 1000, 2000, etc
  private String originalFileName; // represents the original name of the file such as asc1k.dat

  // constructor
  public FileName(String fileName, String fileSize) throws NotValidInputException {
    this.originalFileName = fileName + fileSize + ".dat";
    this.fileName = convertFileNameToString(fileName);
    this.fileSize = convertFileSizeToInt(fileSize);
  }

  /**
   * Converts name of file such as 1k or 2k into the int value representing the object.
   * @param fileSize: the String value representing the file size.
   * @return the int value representing the file size.
   */
  private static int convertFileSizeToInt(String fileSize) throws NotValidInputException {
    if (fileSize.equals("50")) {
      return 50;
    } else if (fileSize.equals("1k") || fileSize.equals("1K")) {
      return 1000;
    } else if (fileSize.equals("2k") || fileSize.equals("2K")) {
      return 2000;
    } else if (fileSize.equals("5k") || fileSize.equals("5K")) {
      return 5000;
    } else if (fileSize.equals("10k") || fileSize.equals("10K")) {
      return 10000;
    } else if (fileSize.equals("20k") || fileSize.equals("20K")) {
      return 20000;
    }
    throw new NotValidInputException("Invalid input file.");
  }

  /**
   * Converts filename to string for easier readability.
   * @param fileName: String value representing the string name such as asc, rev, etc.
   * @return String value representing the file, e.g., ascending, reverse, etc.
   */
  private static String convertFileNameToString(String fileName) throws NotValidInputException {
    if (fileName.equals("asc")) {
      return "ascending";
    } else if (fileName.equals("ran")) {
      return "random";
    } else if (fileName.equals("rev")) {
      return "reverse";
    } else if (fileName.equals("dup")) {
      return "duplicates";
    }
    throw new NotValidInputException("Invalid input file.");
  }

  /**
   * To string method to pretty print the values of this object.
   * @return String value representing object.
   */
  public String toString() {
    return "File ["+ originalFileName +"] sorted in [" + this.fileName + "] order of size [" + this.fileSize + "]";

  }

  // Getters & Setters

  public String getFileName() {
    return fileName;
  }

  public int getFileSize() {
    return fileSize;
  }

  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public void setFileSize(int fileSize) {
    this.fileSize = fileSize;
  }
}
