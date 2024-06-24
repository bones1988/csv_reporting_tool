package com.epam.fileparser.salaryutils;

import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import java.util.Map;

/** This mock implementation is used to make sure that method was not called */
public class SalaryStatisticCalculatorMockMethodsNotImplemented
    implements SalaryStatisticCalculator {
  @Override
  public BigDecimal calculateAverageDependentsSalary(
      Map<Long, Person> employeesMap, Person person) {
    throw new UnsupportedOperationException("calculateAverageDependentsSalary");
  }

  @Override
  public BigDecimal calculateMaxSalary(BigDecimal avgSalary) {
    throw new UnsupportedOperationException("calculateMaxSalary");
  }

  @Override
  public BigDecimal calculateMinSalary(BigDecimal avgSalary) {
    throw new UnsupportedOperationException("calculateMinSalary");
  }
}
