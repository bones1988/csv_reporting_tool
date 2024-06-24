package com.epam.fileparser.personutils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.repository.PersonRepositoryMockReturnEmptyMap;
import com.epam.fileparser.salaryutils.SalaryStatisticCalculator;
import com.epam.fileparser.salaryutils.SalaryStatisticCalculatorMockMethodsNotImplemented;
import com.epam.fileparser.statisticutils.StatisticHolder;
import com.epam.fileparser.statisticutils.StatisticHolderImpl;
import org.junit.jupiter.api.Test;

public class PersonsStatisticCalculatorImplTest {
  private final PersonManagerChainCalculator personManagerChainCalculator =
      new PersonManagerChainCalculatorMockMethodsNotImplemented();
  private final SalaryStatisticCalculator salaryStatisticCalculator =
      new SalaryStatisticCalculatorMockMethodsNotImplemented();
  private final PersonRepository personRepository = new PersonRepositoryMockReturnEmptyMap();
  private final PersonsStatisticCalculatorImpl personStatisticCalculator =
      new PersonsStatisticCalculatorImpl(
          personManagerChainCalculator, salaryStatisticCalculator, personRepository);

  @Test
  public void calculateStatisticShouldReturnEmptyStatisticForEmptyEmployees() {
    // when
    StatisticHolder emptyStatisticHolder = new StatisticHolderImpl();
    StatisticHolder statisticHolder = new StatisticHolderImpl();
    StatisticHolder actual = personStatisticCalculator.calculateStatistic(statisticHolder);

    // then
    assertEquals(emptyStatisticHolder, actual);
  }
}
