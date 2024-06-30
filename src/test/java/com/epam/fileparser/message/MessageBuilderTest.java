package com.epam.fileparser.message;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.MissingResourceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MessageBuilderTest {
  private MessageBuilder messageBuilder;

  @BeforeEach
  void setUp() {
    this.messageBuilder = new MessageBuilderImpl();
  }

  @Test
  void buildMessageShouldThrowMissingResourceExceptionForNotExistingKey() {
    // then
    assertThrows(
        MissingResourceException.class, () -> messageBuilder.buildMessage("not_existing_key"));
  }

  @Test
  void buildMessageShouldReturnMessageForValidKeyWithoutArgs() {
    // given
    String expectedMessage = "message without args";
    String messageKey = "message_without_args";

    // when
    String actual = messageBuilder.buildMessage(messageKey);

    // then
    assertEquals(expectedMessage, actual);
  }

  @Test
  void buildMessageShouldReturnMessageForValidKeyWithOneArg() {
    // given
    String arg = "arg";
    String expectedMessage = "message with arg " + arg;
    String messageKey = "message_with_arg";

    // when
    String actual = messageBuilder.buildMessage(messageKey, arg);

    // then
    assertEquals(expectedMessage, actual);
  }
}
