package com.epam.fileparser.applicationamanager;

import com.epam.fileparser.commandline.CommandLineArgumentParser;
import com.epam.fileparser.componentsmanager.ComponentsContainerInitializer;
import com.epam.fileparser.componentsmanager.container.ComponentsContainer;
import com.epam.fileparser.exceptionhandler.ExceptionHandler;
import com.epam.fileparser.file.FileParser;
import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.model.Person;
import com.epam.fileparser.output.StatisticWriter;
import com.epam.fileparser.person.statisticcalculator.PersonsStatisticCalculator;
import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.statistic.StatisticHolder;
import java.io.IOException;
import java.util.Map;

/**
 * The {@code ApplicationManagerConsoleImpl} class provides an implementation of the {@code
 * ApplicationManager} interface for writing calculated statistic to console. It controls and
 * coordinates the execution flow of the application.
 */
public class ApplicationManagerConsoleImpl implements ApplicationManager {

  private final ComponentsContainerInitializer componentsContainerInitializer;
  private final MessageBuilder messageBuilder;

  public ApplicationManagerConsoleImpl(
      ComponentsContainerInitializer componentsContainerInitializer,
      MessageBuilder messageBuilder) {
    this.componentsContainerInitializer = componentsContainerInitializer;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public void run(String[] args) {
    ComponentsContainer componentsContainer = componentsContainerInitializer.initializeComponents(messageBuilder);
    ExceptionHandler exceptionHandler = componentsContainer.getExceptionHandler();
    try {
      String filePath = getFilePath(componentsContainer, args);

      Map<Long, Person> personsMap = getPersonsFromFile(componentsContainer, filePath);

      savePersons(componentsContainer, personsMap);

      StatisticHolder statisticHolderWithStatistic = calculateStatistic(componentsContainer);

      writeStatistic(componentsContainer, statisticHolderWithStatistic);
    } catch (IOException e) {
      exceptionHandler.handleIOException(e);
    } catch (IllegalArgumentException e) {
      exceptionHandler.handleIllegalArgumentException(e);
    } catch (Exception e) {
      exceptionHandler.handleUnexpectedException(e);
    }
  }

  private String getFilePath(ComponentsContainer componentsContainer, String[] args) {
    CommandLineArgumentParser commandLineArgumentParser =
        componentsContainer.getCommandLineArgumentParser();
    return commandLineArgumentParser.getFilePath(args);
  }

  private Map<Long, Person> getPersonsFromFile(
      ComponentsContainer componentsContainer, String filePath) throws IOException {
    FileParser fileParser = componentsContainer.getFileParser();
    return fileParser.parseFile(filePath);
  }

  private void savePersons(ComponentsContainer componentsContainer, Map<Long, Person> personsMap) {
    PersonRepository personRepository = componentsContainer.getPersonRepository();
    personsMap.values().forEach(personRepository::savePerson);
  }

  private StatisticHolder calculateStatistic(ComponentsContainer componentsContainer) {
    StatisticHolder statisticHolder = componentsContainer.getStatisticHolder();
    PersonsStatisticCalculator personsStatisticCalculator =
        componentsContainer.getPersonStatisticCalculator();
    personsStatisticCalculator.calculateStatistic(statisticHolder);
    return statisticHolder;
  }

  private void writeStatistic(
      ComponentsContainer componentsContainer, StatisticHolder statisticHolder) {
    StatisticWriter statisticWriter = componentsContainer.getStatisticWriter();
    statisticWriter.writeStatistic(statisticHolder);
  }
}
