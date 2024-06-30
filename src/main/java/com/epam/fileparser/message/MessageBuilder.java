package com.epam.fileparser.message;


/**
 * MessageBuilder is an interface to provide formatted messages
 */
public interface MessageBuilder {
    String buildMessage(String key, Object... args);
}
