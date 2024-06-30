package com.epam.fileparser.exceptionhandler;

import java.io.IOException;

/**
 * ExceptionHandler is an interface to provide logic for handling different expected and unexpected exceptions
 */
public interface ExceptionHandler {
  void handleIOException(IOException e);

  void handleIllegalArgumentException(IllegalArgumentException e);

  void handleUnexpectedException(Exception e);
}
