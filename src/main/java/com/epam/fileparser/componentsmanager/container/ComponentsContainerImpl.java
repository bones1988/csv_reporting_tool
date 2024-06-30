package com.epam.fileparser.componentsmanager.container;

import com.epam.fileparser.commandline.CommandLineArgumentParser;
import com.epam.fileparser.exceptionhandler.ExceptionHandler;
import com.epam.fileparser.file.FileParser;
import com.epam.fileparser.output.StatisticWriter;
import com.epam.fileparser.person.statisticcalculator.PersonsStatisticCalculator;
import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.statistic.StatisticHolder;

/**
 * Implementation of ComponentsContainer. Simple storage class to store components and retrieve them
 * when needed. It uses initialized components since application is small.
 */
public class ComponentsContainerImpl implements ComponentsContainer {
  private final CommandLineArgumentParser commandLineArgumentParser;
  private final FileParser fileParser;
  private final PersonRepository personRepository;
  private final StatisticHolder statisticHolder;
  private final PersonsStatisticCalculator personsStatisticCalculator;
  private final StatisticWriter statisticWriter;
  private final ExceptionHandler exceptionHandler;

  public ComponentsContainerImpl(
      CommandLineArgumentParser commandLineArgumentParser,
      FileParser fileParser,
      PersonRepository personRepository,
      StatisticHolder statisticHolder,
      PersonsStatisticCalculator personsStatisticCalculator,
      StatisticWriter statisticWriter,
      ExceptionHandler exceptionHandler) {
    this.commandLineArgumentParser = commandLineArgumentParser;
    this.fileParser = fileParser;
    this.personRepository = personRepository;
    this.statisticHolder = statisticHolder;
    this.personsStatisticCalculator = personsStatisticCalculator;
    this.statisticWriter = statisticWriter;
    this.exceptionHandler = exceptionHandler;
  }

  public CommandLineArgumentParser getCommandLineArgumentParser() {
    return this.commandLineArgumentParser;
  }

  @Override
  public FileParser getFileParser() {
    return this.fileParser;
  }

  @Override
  public PersonRepository getPersonRepository() {
    return this.personRepository;
  }

  @Override
  public StatisticHolder getStatisticHolder() {
    return this.statisticHolder;
  }

  @Override
  public PersonsStatisticCalculator getPersonStatisticCalculator() {
    return this.personsStatisticCalculator;
  }

  @Override
  public StatisticWriter getStatisticWriter() {
    return this.statisticWriter;
  }

  @Override
  public ExceptionHandler getExceptionHandler() {
    return this.exceptionHandler;
  }
}
