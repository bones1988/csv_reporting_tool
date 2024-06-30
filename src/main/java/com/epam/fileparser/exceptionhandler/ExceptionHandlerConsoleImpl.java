package com.epam.fileparser.exceptionhandler;

import com.epam.fileparser.message.MessageBuilder;
import java.io.IOException;

/**
 * Realization of ExceptionHandler interface. Just writes to console user-friendly message and
 * stacktrace after it.
 */
public class ExceptionHandlerConsoleImpl implements ExceptionHandler {
  private static final String IO_ERROR_MESSAGE_KEY = "io_error";
  private static final String ILLEGAL_ARGUMENT_MESSAGE_KEY = "illegal_argument_error";
  private static final String UNEXPECTED_ERROR_MESSAGE_KEY = "unexpected_error";

  private final MessageBuilder messageBuilder;

  public ExceptionHandlerConsoleImpl(MessageBuilder messageBuilder) {
    this.messageBuilder = messageBuilder;
  }

  @Override
  public void handleIOException(IOException e) {
    String message = messageBuilder.buildMessage(IO_ERROR_MESSAGE_KEY);
    writeMessage(message, e);
  }

  @Override
  public void handleIllegalArgumentException(IllegalArgumentException e) {
    String message = messageBuilder.buildMessage(ILLEGAL_ARGUMENT_MESSAGE_KEY, e.getMessage());
    writeMessage(message, e);
  }

  @Override
  public void handleUnexpectedException(Exception e) {
    String message = messageBuilder.buildMessage(UNEXPECTED_ERROR_MESSAGE_KEY, e);
    writeMessage(message, e);
  }

  private void writeMessage(String message, Throwable e) {
    System.err.println(message);
    e.printStackTrace();
  }
}
