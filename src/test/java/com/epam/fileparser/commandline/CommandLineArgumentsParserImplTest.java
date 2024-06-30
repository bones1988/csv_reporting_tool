package com.epam.fileparser.commandline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.message.MessageBuilderMockImplReturnMessage;
import org.junit.jupiter.api.Test;

class CommandLineArgumentsParserImplTest {
  private final MessageBuilder messageBuilderMock = new MessageBuilderMockImplReturnMessage();
  private final CommandLineArgumentParser commandLineArgumentParser =
      new CommandLineArgumentParserImpl(messageBuilderMock);

  @Test
  void getFilePathShouldReturnFilePath() {
    // given
    String[] args = {"FilePath"};

    // when
    String expected = "FilePath";
    String actual = commandLineArgumentParser.getFilePath(args);

    // then
    assertEquals(expected, actual);
  }

  @Test
  void getFilePathShouldThrowIllegalArgumentExceptionWhenMoreThanOneArgumentProvided() {
    // given
    String[] args = {"FilePath", "secondArgument"};

    // then
    assertThrows(IllegalArgumentException.class, () -> commandLineArgumentParser.getFilePath(args));
  }

  @Test
  void getFilePathShouldThrowIllegalArgumentExceptionWhenZeroArgumentsProvided() {
    // given
    String[] args = new String[0];

    // then
    assertThrows(IllegalArgumentException.class, () -> commandLineArgumentParser.getFilePath(args));
  }
}
