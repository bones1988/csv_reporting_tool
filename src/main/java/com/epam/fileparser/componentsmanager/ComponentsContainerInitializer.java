package com.epam.fileparser.componentsmanager;

import com.epam.fileparser.componentsmanager.container.ComponentsContainer;
import com.epam.fileparser.message.MessageBuilder;

/**
 * Interface for a class that initializes components necessary for the application and returns
 * container to get them.
 */
public interface ComponentsContainerInitializer {
  /**
   * Initializes the components of the application and encapsulates them in a ComponentsContainer.
   *
   * <p>The components include objects for parsing command line arguments, parsing files, handling
   * persons' repository, holding statistics, calculating persons' statistics, exception handling
   * and writing statistics.
   *
   * @return a ComponentsContainer containing the initialized components.
   */
  ComponentsContainer initializeComponents(MessageBuilder messageBuilder);
}
