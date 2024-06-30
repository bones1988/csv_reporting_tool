package com.epam.fileparser.message;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Implementation of MessageBuilder interface to build message from properties source
 */
public class MessageBuilderImpl implements MessageBuilder {
  private static final String MESSAGES_BUNDLE_NAME = "messages";
  private static final ResourceBundle MESSAGES_BUNDLE =
      ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME);
  private static final int ZERO_ARGS_LENGTH = 0;

  public String buildMessage(String key, Object... args) {
    String pattern = MESSAGES_BUNDLE.getString(key);
    if (args.length == ZERO_ARGS_LENGTH) {
      return pattern;
    }
    return MessageFormat.format(pattern, args);
  }
}
