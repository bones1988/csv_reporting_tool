package com.epam.fileparser.salaryutils;

import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import java.util.Map;

public interface SalaryStatisticCalculator {
  BigDecimal calculateAverageSalary(Map<Long, Person> employeesMap, Person person);

  BigDecimal calculateMaxSalary(BigDecimal avgSalary);

  BigDecimal calculateMinSalary(BigDecimal avgSalary);
}
