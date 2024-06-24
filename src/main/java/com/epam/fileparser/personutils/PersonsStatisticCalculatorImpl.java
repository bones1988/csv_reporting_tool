package com.epam.fileparser.personutils;

import com.epam.fileparser.model.Person;
import com.epam.fileparser.repository.PersonRepository;
import com.epam.fileparser.salaryutils.SalaryStatisticCalculator;
import com.epam.fileparser.statisticutils.StatisticHolder;
import com.epam.fileparser.statisticutils.model.ManagerStatisticHolder;
import com.epam.fileparser.statisticutils.model.SalaryStatisticHolder;
import java.math.BigDecimal;
import java.util.Map;

/**
 * PersonsStatisticCalculatorImpl provides a simple concrete implementation of the
 * PersonsStatisticCalculator interface. It calculates statistics based on data in the provided
 * StatisticHolder object.
 */
public class PersonsStatisticCalculatorImpl implements PersonsStatisticCalculator {
  private static final int MAX_MANAGERS_CHAIN = 4;
  private final PersonManagerChainCalculator personManagerChainCalculator;
  private final SalaryStatisticCalculator salaryStatisticCalculator;
  private final PersonRepository personRepository;

  public PersonsStatisticCalculatorImpl(
      PersonManagerChainCalculator personManagerChainCalculator,
      SalaryStatisticCalculator salaryStatisticCalculator,
      PersonRepository personRepository) {
    this.personManagerChainCalculator = personManagerChainCalculator;
    this.salaryStatisticCalculator = salaryStatisticCalculator;
    this.personRepository = personRepository;
  }

  @Override
  public StatisticHolder calculateStatistic(StatisticHolder statisticHolder) {
    Map<Long, Person> employeesMap = personRepository.getAllPersons();
    employeesMap
        .values()
        .forEach(person -> calculateForEmployee(statisticHolder, person, employeesMap));
    return statisticHolder;
  }

  private void calculateForEmployee(
      StatisticHolder statisticHolder, Person person, Map<Long, Person> employeesMap) {
    calculateManagersChain(statisticHolder, person, employeesMap);
    calculateSalaryStatistic(statisticHolder, person, employeesMap);
  }

  private void calculateManagersChain(
      StatisticHolder statisticHolder, Person person, Map<Long, Person> personMap) {
    int managersChain = personManagerChainCalculator.calculateManagersChain(personMap, person);
    if (managersChain > MAX_MANAGERS_CHAIN) {
      ManagerStatisticHolder managerStatisticHolder = new ManagerStatisticHolder();
      managerStatisticHolder.setPerson(person);
      managerStatisticHolder.setByNumber(managersChain - MAX_MANAGERS_CHAIN);
      statisticHolder.addWrongManagerChainPerson(managerStatisticHolder);
    }
  }

  private void calculateSalaryStatistic(
      StatisticHolder statisticHolder, Person person, Map<Long, Person> personsMap) {
    BigDecimal avgSubordinatesSalary =
        salaryStatisticCalculator.calculateAverageDependentsSalary(personsMap, person);
    BigDecimal maxSalary = salaryStatisticCalculator.calculateMaxSalary(avgSubordinatesSalary);
    BigDecimal minSalary = salaryStatisticCalculator.calculateMinSalary(avgSubordinatesSalary);
    if (person.getSalary().compareTo(minSalary) < 0) {
      SalaryStatisticHolder salaryStatisticHolder = new SalaryStatisticHolder();
      salaryStatisticHolder.setPerson(person);
      salaryStatisticHolder.setBySalary(minSalary.subtract(person.getSalary()));
      statisticHolder.addLowSalaryStatistic(salaryStatisticHolder);
    } else {
      if (person.getSalary().compareTo(maxSalary) > 0) {
        SalaryStatisticHolder salaryStatisticHolder = new SalaryStatisticHolder();
        salaryStatisticHolder.setPerson(person);
        salaryStatisticHolder.setBySalary(person.getSalary().subtract(maxSalary));
        statisticHolder.addHighSalary(salaryStatisticHolder);
      }
    }
  }
}
