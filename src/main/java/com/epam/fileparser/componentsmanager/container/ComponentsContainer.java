package com.epam.fileparser.componentsmanager.container;

import com.epam.fileparser.commandline.CommandLineArgumentParser;
import com.epam.fileparser.exceptionhandler.ExceptionHandler;
import com.epam.fileparser.file.FileParser;
import com.epam.fileparser.output.StatisticWriter;
import com.epam.fileparser.person.statisticcalculator.PersonsStatisticCalculator;
import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.statistic.StatisticHolder;

/**
 * ComponentsContainer interface provides methods for obtaining the core components of the system.
 */
public interface ComponentsContainer {
  CommandLineArgumentParser getCommandLineArgumentParser();

  FileParser getFileParser();

  PersonRepository getPersonRepository();

  StatisticHolder getStatisticHolder();

  PersonsStatisticCalculator getPersonStatisticCalculator();

  StatisticWriter getStatisticWriter();

  ExceptionHandler getExceptionHandler();
}
