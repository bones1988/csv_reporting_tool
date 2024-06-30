package com.epam.fileparser.componentsmanager;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.fileparser.commandline.CommandLineArgumentParser;
import com.epam.fileparser.commandline.CommandLineArgumentParserImpl;
import com.epam.fileparser.componentsmanager.container.ComponentsContainer;
import com.epam.fileparser.exceptionhandler.ExceptionHandler;
import com.epam.fileparser.exceptionhandler.ExceptionHandlerConsoleImpl;
import com.epam.fileparser.file.CSVFileParser;
import com.epam.fileparser.file.FileParser;
import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.message.MessageBuilderMockImplReturnMessage;
import com.epam.fileparser.output.ConsoleStatisticWriter;
import com.epam.fileparser.output.StatisticWriter;
import com.epam.fileparser.person.statisticcalculator.PersonsStatisticCalculator;
import com.epam.fileparser.person.statisticcalculator.PersonsStatisticCalculatorImpl;
import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.repository.PersonRepositoryInMemory;
import com.epam.fileparser.statistic.StatisticHolder;
import com.epam.fileparser.statistic.StatisticHolderImpl;
import org.junit.jupiter.api.Test;

class ComponentsContainerInitializerImplTest {
  private final ComponentsContainerInitializer initializer =
      new ComponentsContainerInitializerImpl();
  private final MessageBuilder messageBuilder = new MessageBuilderMockImplReturnMessage();

  @Test
  void initializeShouldInitializeComponentsContainer() {
    // given
    ComponentsContainer componentsContainer = initializer.initializeComponents(messageBuilder);

    // when
    CommandLineArgumentParser actualCommandLineArgumentParser =
        componentsContainer.getCommandLineArgumentParser();
    FileParser actualFileParser = componentsContainer.getFileParser();
    PersonRepository actualPersonRepository = componentsContainer.getPersonRepository();
    StatisticHolder actualStatisticHolder = componentsContainer.getStatisticHolder();
    PersonsStatisticCalculator actualPersonsStatisticCalculator =
        componentsContainer.getPersonStatisticCalculator();
    StatisticWriter actualStatisticWriter = componentsContainer.getStatisticWriter();
    ExceptionHandler actualExceptionHandler = componentsContainer.getExceptionHandler();

    // then
    assertNotNull(componentsContainer);
    assertInstanceOf(CommandLineArgumentParserImpl.class, actualCommandLineArgumentParser);
    assertInstanceOf(CSVFileParser.class, actualFileParser);
    assertInstanceOf(PersonRepositoryInMemory.class, actualPersonRepository);
    assertInstanceOf(StatisticHolderImpl.class, actualStatisticHolder);
    assertInstanceOf(PersonsStatisticCalculatorImpl.class, actualPersonsStatisticCalculator);
    assertInstanceOf(ConsoleStatisticWriter.class, actualStatisticWriter);
    assertInstanceOf(ExceptionHandlerConsoleImpl.class, actualExceptionHandler);
  }
}
