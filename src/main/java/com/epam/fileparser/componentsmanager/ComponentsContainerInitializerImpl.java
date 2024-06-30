package com.epam.fileparser.componentsmanager;

import com.epam.fileparser.commandline.CommandLineArgumentParser;
import com.epam.fileparser.commandline.CommandLineArgumentParserImpl;
import com.epam.fileparser.componentsmanager.container.ComponentsContainer;
import com.epam.fileparser.componentsmanager.container.ComponentsContainerImpl;
import com.epam.fileparser.exceptionhandler.ExceptionHandler;
import com.epam.fileparser.exceptionhandler.ExceptionHandlerConsoleImpl;
import com.epam.fileparser.file.CSVFileParser;
import com.epam.fileparser.file.FileParser;
import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.output.ConsoleStatisticWriter;
import com.epam.fileparser.output.StatisticWriter;
import com.epam.fileparser.person.managerchaincalculator.PersonManagerChainCalculator;
import com.epam.fileparser.person.managerchaincalculator.PersonManagerChainCalculatorImpl;
import com.epam.fileparser.person.mapper.PersonMapper;
import com.epam.fileparser.person.mapper.PersonMapperCSV;
import com.epam.fileparser.person.statisticcalculator.PersonsStatisticCalculator;
import com.epam.fileparser.person.statisticcalculator.PersonsStatisticCalculatorImpl;
import com.epam.fileparser.person.validator.PersonDataValidator;
import com.epam.fileparser.person.validator.PersonDataValidatorImpl;
import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.repository.PersonRepositoryInMemory;
import com.epam.fileparser.salary.SalaryStatisticCalculator;
import com.epam.fileparser.salary.SalaryStatisticCalculatorImpl;
import com.epam.fileparser.statistic.StatisticHolder;
import com.epam.fileparser.statistic.StatisticHolderImpl;

/**
 * This class {@code ComponentsContainerInitializerImpl} provides an implementation for the {@code
 * ComponentsContainerInitializer} interface. It initializes necessary components used within the
 * application.
 */
public class ComponentsContainerInitializerImpl implements ComponentsContainerInitializer {

  @Override
  public ComponentsContainer initializeComponents(MessageBuilder messageBuilder) {
    CommandLineArgumentParser commandLineArgumentParser =
        getCommandLineArgumentParser(messageBuilder);

    PersonMapper personMapper = getPersonMapper(messageBuilder);
    PersonDataValidator personDataValidator = getPersonDataValidator();
    FileParser fileParser = initializeFileParser(personMapper, personDataValidator, messageBuilder);

    PersonRepository personRepository = getPersonRepository();
    StatisticHolder statisticHolder = getStatisticHolder();
    PersonManagerChainCalculator personManagerChainCalculator =
        getPersonManagerChainCalculator(messageBuilder);
    SalaryStatisticCalculator salaryStatisticCalculator = getSalaryStatisticCalculator();
    PersonsStatisticCalculator personsStatisticCalculator =
        getPersonStatisticCalculator(
            personManagerChainCalculator, salaryStatisticCalculator, personRepository);

    StatisticWriter statisticWriter = getStatisticWriter(messageBuilder);
    ExceptionHandler exceptionHandler = getExceptionHandler(messageBuilder);
    return new ComponentsContainerImpl(
        commandLineArgumentParser,
        fileParser,
        personRepository,
        statisticHolder,
        personsStatisticCalculator,
        statisticWriter,
        exceptionHandler);
  }

  private FileParser initializeFileParser(
      PersonMapper personMapper,
      PersonDataValidator personDataValidator,
      MessageBuilder messageBuilder) {
    return new CSVFileParser(personMapper, personDataValidator, messageBuilder);
  }

  private PersonMapper getPersonMapper(MessageBuilder messageBuilder) {
    return new PersonMapperCSV(messageBuilder);
  }

  private PersonDataValidator getPersonDataValidator() {
    return new PersonDataValidatorImpl();
  }

  private CommandLineArgumentParser getCommandLineArgumentParser(MessageBuilder messageBuilder) {
    return new CommandLineArgumentParserImpl(messageBuilder);
  }

  private StatisticHolder getStatisticHolder() {
    return new StatisticHolderImpl();
  }

  private StatisticWriter getStatisticWriter(MessageBuilder messageBuilder) {
    return new ConsoleStatisticWriter(messageBuilder);
  }

  private PersonsStatisticCalculator getPersonStatisticCalculator(
      PersonManagerChainCalculator personManagerChainCalculator,
      SalaryStatisticCalculator salaryStatisticCalculator,
      PersonRepository personRepository) {

    return new PersonsStatisticCalculatorImpl(
        personManagerChainCalculator, salaryStatisticCalculator, personRepository);
  }

  private PersonRepository getPersonRepository() {
    return PersonRepositoryInMemory.getInstance();
  }

  private PersonManagerChainCalculator getPersonManagerChainCalculator(
      MessageBuilder messageBuilder) {
    return new PersonManagerChainCalculatorImpl(messageBuilder);
  }

  private ExceptionHandler getExceptionHandler(MessageBuilder messageBuilder) {
    return new ExceptionHandlerConsoleImpl(messageBuilder);
  }

  private SalaryStatisticCalculator getSalaryStatisticCalculator() {
    return new SalaryStatisticCalculatorImpl();
  }
}
