package com.epam.fileparser;

import com.epam.fileparser.applicationamanager.ApplicationManager;
import com.epam.fileparser.applicationamanager.ApplicationManagerConsoleImpl;
import com.epam.fileparser.componentsmanager.ComponentsContainerInitializer;
import com.epam.fileparser.componentsmanager.ComponentsContainerInitializerImpl;
import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.message.MessageBuilderImpl;

/**
 * ConsoleRunner is the entry point of this program. This class includes a main method, which
 * creates necessary objects and starts the program.
 */
public class ConsoleRunner {

  /**
   * The application starts running from the main() method. This method creates necessary objects,
   * perform operations and runs the program. path to file should be provided as first argument
   *
   * @param args an array of command-line arguments for the application
   */
  public static void main(String[] args) {
    ComponentsContainerInitializer componentsContainerInitializer =
        new ComponentsContainerInitializerImpl();
    MessageBuilder messageBuilder = new MessageBuilderImpl();
    ApplicationManager applicationManager =
        new ApplicationManagerConsoleImpl(componentsContainerInitializer, messageBuilder);

    applicationManager.run(args);
  }
}
