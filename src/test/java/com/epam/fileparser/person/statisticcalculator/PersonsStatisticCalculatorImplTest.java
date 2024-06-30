package com.epam.fileparser.person.statisticcalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.fileparser.person.managerchaincalculator.PersonManagerChainCalculator;
import com.epam.fileparser.person.managerchaincalculator.PersonManagerChainCalculatorMockMethodsNotImplemented;
import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.repository.PersonRepositoryMockReturnEmptyMap;
import com.epam.fileparser.salary.SalaryStatisticCalculator;
import com.epam.fileparser.salary.SalaryStatisticCalculatorMockMethodsNotImplemented;
import com.epam.fileparser.statistic.StatisticHolder;
import com.epam.fileparser.statistic.StatisticHolderImpl;
import org.junit.jupiter.api.Test;

class PersonsStatisticCalculatorImplTest {
  private final PersonManagerChainCalculator personManagerChainCalculator =
      new PersonManagerChainCalculatorMockMethodsNotImplemented();
  private final SalaryStatisticCalculator salaryStatisticCalculator =
      new SalaryStatisticCalculatorMockMethodsNotImplemented();
  private final PersonRepository personRepository = new PersonRepositoryMockReturnEmptyMap();
  private final PersonsStatisticCalculatorImpl personStatisticCalculator =
      new PersonsStatisticCalculatorImpl(
          personManagerChainCalculator, salaryStatisticCalculator, personRepository);

  @Test
  void calculateStatisticShouldReturnEmptyStatisticForEmptyEmployees() {
    // when
    StatisticHolder emptyStatisticHolder = new StatisticHolderImpl();
    StatisticHolder statisticHolder = new StatisticHolderImpl();
    StatisticHolder actual = personStatisticCalculator.calculateStatistic(statisticHolder);

    // then
    assertEquals(emptyStatisticHolder, actual);
  }
}
