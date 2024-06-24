package com.epam.fileparser.salaryutils;

import static com.epam.fileparser.runner.ConsoleRunner.DECIMAL_SCALE;
import static com.epam.fileparser.runner.ConsoleRunner.ROUNDING_MODE;

import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

/**
 * SalaryStatisticCalculatorImpl provides a default implementation of the SalaryStatisticCalculator
 * interface methods. It is designed to calculate statistical salary information, such as average,
 * maximum and minimum salaries.
 */
public class SalaryStatisticCalculatorImpl implements SalaryStatisticCalculator {
  private static final String MAX_SALARY_PERCENT = "1.50";
  private static final String MIN_SALARY_PERCENT = "1.20";

  @Override
  public BigDecimal calculateAverageDependentsSalary(
      Map<Long, Person> employeesMap, Person person) {
    List<BigDecimal> salaries =
        employeesMap.values().stream()
            .filter(p -> p instanceof Employee)
            .map(p -> (Employee) p)
            .filter(e -> e.getManagerId().equals(person.getId()))
            .map(Employee::getSalary)
            .toList();

    BigDecimal salarySum = BigDecimal.ZERO;
    for (BigDecimal s : salaries) {
      salarySum = salarySum.add(s).setScale(DECIMAL_SCALE, ROUNDING_MODE);
    }
    if (!salarySum.equals(BigDecimal.ZERO)) {
      return salarySum.divide(new BigDecimal(salaries.size()), 2, RoundingMode.HALF_UP);
    } else {
      return BigDecimal.ZERO.setScale(DECIMAL_SCALE, ROUNDING_MODE);
    }
  }

  @Override
  public BigDecimal calculateMaxSalary(BigDecimal avgSalary) {
    BigDecimal percentageToAdd = new BigDecimal(MAX_SALARY_PERCENT);
    return avgSalary.multiply(percentageToAdd).setScale(DECIMAL_SCALE, ROUNDING_MODE);
  }

  @Override
  public BigDecimal calculateMinSalary(BigDecimal avgSalary) {
    BigDecimal percentageToAdd = new BigDecimal(MIN_SALARY_PERCENT);
    return avgSalary.multiply(percentageToAdd).setScale(DECIMAL_SCALE, ROUNDING_MODE);
  }
}
