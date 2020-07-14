package com.sk.sorts.utils;

/**
 * Exception class to handle invalid inputs provided for this project.
 *
 * @author Samra Kasim
 */

public class NotValidInputException extends Exception {
  /**
   * Constructor method to instantiate when needing to throw an exception for invalid input.
   * @param message
   */
  public NotValidInputException(String message) {
    super(message);
  }
}

