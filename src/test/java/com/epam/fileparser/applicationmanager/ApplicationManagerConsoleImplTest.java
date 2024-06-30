package com.epam.fileparser.applicationmanager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.epam.fileparser.applicationamanager.ApplicationManager;
import com.epam.fileparser.applicationamanager.ApplicationManagerConsoleImpl;
import com.epam.fileparser.componentsmanager.ComponentsContainerInitializer;
import com.epam.fileparser.componentsmanager.ComponentsContainerInitializerImpl;
import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.message.MessageBuilderMockImplReturnMessage;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

/** High level tests to check that application is running correctly */
class ApplicationManagerConsoleImplTest {
  private final ComponentsContainerInitializer componentsContainerInitializer =
      new ComponentsContainerInitializerImpl();
  private final MessageBuilder messageBuilderMock = new MessageBuilderMockImplReturnMessage();
  private final ApplicationManager applicationManager =
      new ApplicationManagerConsoleImpl(componentsContainerInitializer, messageBuilderMock);

  @Test
  void runShouldRunWithoutExceptionsWithValidArgs() throws URISyntaxException {
    // given
    URL res = getClass().getClassLoader().getResource("correct_file.csv");
    String correctFilePath = Paths.get(res.toURI()).toString();
    String[] args = {correctFilePath};

    // then
    assertDoesNotThrow(() -> applicationManager.run(args));
  }

  @Test
  void runShouldRunWithoutExceptionsWithInValidArgs() {
    // given
    String[] args = new String[0];

    // then
    assertDoesNotThrow(() -> applicationManager.run(args));
  }
}
