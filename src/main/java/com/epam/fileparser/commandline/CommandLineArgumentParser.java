package com.epam.fileparser.commandline;

/**
 * This interface represents a command line argument parser. It provides a mechanism to extract the
 * file path from the command line arguments.
 */
public interface CommandLineArgumentParser {

  /**
   * Extracts and returns the file path from the provided command line arguments.
   *
   * @param args the command line arguments.
   * @return the file path as a string.
   * @throws IllegalArgumentException if the file path is not present in the arguments.
   */
  String getFilePath(String[] args);
}
