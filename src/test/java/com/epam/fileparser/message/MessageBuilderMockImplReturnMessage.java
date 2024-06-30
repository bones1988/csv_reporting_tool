package com.epam.fileparser.message;

/**
 * Mock implementation of MessageBuilder just returns message provided
 */
public class MessageBuilderMockImplReturnMessage implements MessageBuilder {
    @Override
    public String buildMessage(String key, Object... args) {
        return key;
    }
}
