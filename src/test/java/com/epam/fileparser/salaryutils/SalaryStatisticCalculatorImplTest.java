package com.epam.fileparser.salaryutils;

import static com.epam.fileparser.runner.ConsoleRunner.DECIMAL_SCALE;
import static com.epam.fileparser.runner.ConsoleRunner.ROUNDING_MODE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class SalaryStatisticCalculatorImplTest {
  private final SalaryStatisticCalculator salaryStatisticCalculator =
      new SalaryStatisticCalculatorImpl();

  @Test
  public void calculateAverageDependentsSalaryShouldReturnZeroIfPersonMapIsEmpty() {
    // given
    Person employeeOne = new Employee(1, "First", "Last", new BigDecimal(1), 1L);
    Map<Long, Person> emptyMap = Collections.EMPTY_MAP;

    // when
    BigDecimal actual =
        salaryStatisticCalculator.calculateAverageDependentsSalary(emptyMap, employeeOne);

    // then
    assertEquals(BigDecimal.ZERO.setScale(DECIMAL_SCALE, ROUNDING_MODE), actual);
  }

  @Test
  public void calculateAverageDependentsSalaryShouldReturnOneHundredFifty() {
    // given
    Person ceo = new CEO(1, "First", "Last", new BigDecimal(1));
    Person employeeTwo = new Employee(2, "First", "Last", new BigDecimal(100), 1L);
    Person employeeThree = new Employee(3, "First", "Last", new BigDecimal(200), 1L);
    Person employeeFour = new Employee(4, "First", "Last", new BigDecimal(100), 3L);
    Map<Long, Person> personMap = new HashMap<>();
    personMap.put(employeeTwo.getId(), employeeTwo);
    personMap.put(employeeThree.getId(), employeeThree);
    personMap.put(employeeFour.getId(), employeeFour);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateAverageDependentsSalary(personMap, ceo);

    // then
    assertEquals(new BigDecimal(150).setScale(DECIMAL_SCALE, ROUNDING_MODE), actual);
  }

  @Test
  public void calculateMaxSalaryShouldReturnZeroIfSalaryZero() {
    // given
    BigDecimal zeroSalary = BigDecimal.ZERO.setScale(DECIMAL_SCALE, ROUNDING_MODE);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateMaxSalary(zeroSalary);

    // then
    assertEquals(zeroSalary, actual);
  }

  @Test
  public void calculateMaxSalaryShouldReturnFiftyPercentMore() {
    // given
    BigDecimal salary = new BigDecimal(100).setScale(DECIMAL_SCALE, ROUNDING_MODE);
    BigDecimal expected = new BigDecimal(150).setScale(DECIMAL_SCALE, ROUNDING_MODE);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateMaxSalary(salary);

    // then
    assertEquals(expected, actual);
  }

  @Test
  public void calculateMinSalaryShouldReturnZeroIfSalaryZero() {
    // given
    BigDecimal zeroSalary = BigDecimal.ZERO.setScale(DECIMAL_SCALE, ROUNDING_MODE);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateMinSalary(zeroSalary);

    // then
    assertEquals(zeroSalary, actual);
  }

  @Test
  public void calculateMinSalaryShouldReturnTwentyPercentMore() {
    // given
    BigDecimal salary = new BigDecimal(100).setScale(DECIMAL_SCALE, ROUNDING_MODE);
    BigDecimal expected = new BigDecimal(120).setScale(DECIMAL_SCALE, ROUNDING_MODE);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateMinSalary(salary);

    // then
    assertEquals(expected, actual);
  }
}
