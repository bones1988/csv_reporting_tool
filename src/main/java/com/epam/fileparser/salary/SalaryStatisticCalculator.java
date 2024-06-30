package com.epam.fileparser.salary;

import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import java.util.Map;

/**
 * SalaryStatisticCalculator is an interface defining methods for calculating statistical
 * information about salaries of Person objects.
 */
public interface SalaryStatisticCalculator {

  /**
   * Calculates the average salary of direct dependents.
   *
   * @param employeesMap - A map of employees where key is the person's id and value is the Person
   *     object.
   * @param person - The person for whom the average salary calculation is made.
   * @return The average salary from the provided map.
   */
  BigDecimal calculateAverageDependentsSalary(Map<Long, Person> employeesMap, Person person);

  /**
   * Calculates the maximum salary based on the given average dependents salary.
   *
   * @param avgSalary - The average salary value.
   * @return The maximum salary.
   */
  BigDecimal calculateMaxSalary(BigDecimal avgSalary);

  /**
   * Calculates the minimum salary based on the given average dependents salary.
   *
   * @param avgSalary - The average salary value.
   * @return The minimum salary.
   */
  BigDecimal calculateMinSalary(BigDecimal avgSalary);
}
