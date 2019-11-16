package com.sk.sorts.utils;

public class FileName {

  private String fileName;
  private int fileSize;
  private String originalFileName;

  public FileName(String fileName, String fileSize) {
    this.originalFileName = fileName + fileSize + ".dat";
    this.fileName = convertFileNameToString(fileName);
    this.fileSize = convertFileSizeToInt(fileSize);
  }

  private static int convertFileSizeToInt(String fileSize) {
    if (fileSize.equals("50")) {
      return 50;
    } else if (fileSize.equals("1k")) {
      return 1000;
    } else if (fileSize.equals("2k")) {
      return 2000;
    } else if (fileSize.equals("5k")) {
      return 5000;
    } else if (fileSize.equals("10k")) {
      return 10000;
    } else if (fileSize.equals("20k")) {
      return 20000;
    }
    return 0;
  }

  private static String convertFileNameToString(String fileName) {
    if (fileName.equals("asc")) {
      return "ascending";
    } else if (fileName.equals("ran")) {
      return "random";
    } else if (fileName.equals("rev")) {
      return "reverse";
    }
    return "null";
  }

  public String toString() {
    return "File ["+ originalFileName +"] sorted in [" + this.fileName + "] order of size [" + this.fileSize + "]";

  }
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
