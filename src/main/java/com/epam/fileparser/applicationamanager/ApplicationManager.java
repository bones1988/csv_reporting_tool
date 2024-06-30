package com.epam.fileparser.applicationamanager;


/**
 * ApplicationManager interface represents an Application Manager that controls the execution flow of the
 * application. It includes only a run method to run the application.
 */
public interface ApplicationManager {

  /**
   * Launches the application with the provided command-line arguments. It initializes the necessary
   * components and performs the primary tasks such as parsing files and writing statistics.
   *
   * @param args command-line arguments given to the application. The expected arguments should be
   *     properly formatted:
   *     <ul>
   *       <li>arg[0]: The file path for input data.
   *     </ul>
   */
  void run(String[] args);
}
