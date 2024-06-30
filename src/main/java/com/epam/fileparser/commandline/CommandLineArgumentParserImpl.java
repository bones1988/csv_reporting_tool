package com.epam.fileparser.commandline;

import com.epam.fileparser.message.MessageBuilder;

/**
 * This class provides an implementation of the CommandLineArgumentParser interface. It extracts the
 * file path from the provided command line arguments.
 */
public class CommandLineArgumentParserImpl implements CommandLineArgumentParser {
  private static final int EXPECTED_COMMAND_LINE_ARGUMENTS_NUMBER = 1;
  private static final int FILE_PATH_INDEX = 0;
  private static final String PROVIDE_FILE_PATH_MESSAGE_KEY = "provide_file_path";
  private final MessageBuilder messageBuilder;

  public CommandLineArgumentParserImpl(MessageBuilder messageBuilder) {
    this.messageBuilder = messageBuilder;
  }

  @Override
  public String getFilePath(String[] args) {
    if (args.length != EXPECTED_COMMAND_LINE_ARGUMENTS_NUMBER) {
      throw new IllegalArgumentException(
          messageBuilder.buildMessage(PROVIDE_FILE_PATH_MESSAGE_KEY));
    }
    return args[FILE_PATH_INDEX];
  }
}
