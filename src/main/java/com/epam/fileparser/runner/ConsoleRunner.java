package com.epam.fileparser.runner;

import com.epam.fileparser.fileutils.CSVFileParser;
import com.epam.fileparser.fileutils.FileParser;
import com.epam.fileparser.model.Person;
import com.epam.fileparser.outpututils.ConsoleStatisticWriter;
import com.epam.fileparser.outpututils.StatisticWriter;
import com.epam.fileparser.personutils.*;
import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.repository.PersonRepositoryInMemory;
import com.epam.fileparser.salaryutils.SalaryStatisticCalculator;
import com.epam.fileparser.salaryutils.SalaryStatisticCalculatorImpl;
import com.epam.fileparser.statisticutils.StatisticHolder;
import com.epam.fileparser.statisticutils.StatisticHolderImpl;
import java.math.RoundingMode;
import java.util.Map;

public class ConsoleRunner {
  public static final int DECIMAL_SCALE = 2;
  public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Please provide path to file you want to parse");
      return;
    }
    String filePath = args[0];
    System.out.println("Processing...");
    try {
      PersonMapper personMapper = new PersonMapperCSV();
      PersonDataValidator personDataValidator = new PersonDataValidatorImpl();
      FileParser fileParser = new CSVFileParser(personMapper, personDataValidator);
      PersonRepository personRepository = PersonRepositoryInMemory.getInstance();
      Map<Long, Person> employees = fileParser.parseFile(filePath);
      employees.values().forEach(personRepository::savePerson);

      StatisticHolder statisticHolder = getStatisticHolder(personRepository);

      StatisticWriter statisticWriter = new ConsoleStatisticWriter();
      statisticWriter.writeStatistic(statisticHolder);
    } catch (Exception e) {
      System.out.println("During processing file error occurred:");
      System.out.println(e.getMessage());
    }
  }

  private static StatisticHolder getStatisticHolder(PersonRepository personRepository) {
    SalaryStatisticCalculator salaryStatisticCalculator = new SalaryStatisticCalculatorImpl();
    PersonManagerChainCalculator personManagerChainCalculator =
        new PersonManagerChainCalculatorImpl();
    PersonsStatisticCalculator personsStatisticCalculator =
        new PersonsStatisticCalculatorImpl(
            personManagerChainCalculator, salaryStatisticCalculator, personRepository);
    StatisticHolder statisticHolder = new StatisticHolderImpl();
    return personsStatisticCalculator.calculateStatistic(statisticHolder);
  }
}
