package com.epam.fileparser.salary;

import static com.epam.fileparser.constants.ApplicationConstants.DECIMAL_SCALE;
import static com.epam.fileparser.constants.ApplicationConstants.ROUNDING_MODE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SalaryStatisticCalculatorImplTest {
  private final SalaryStatisticCalculator salaryStatisticCalculator =
      new SalaryStatisticCalculatorImpl();

  @Test
  void calculateAverageDependentsSalaryShouldReturnZeroIfPersonMapIsEmpty() {
    // given
    Person employeeOne = new Employee(1, "First", "Last", new BigDecimal(1), 1L);
    Map<Long, Person> emptyMap = Collections.emptyMap();

    // when
    BigDecimal actual =
        salaryStatisticCalculator.calculateAverageDependentsSalary(emptyMap, employeeOne);

    // then
    assertEquals(BigDecimal.ZERO.setScale(DECIMAL_SCALE, ROUNDING_MODE), actual);
  }

  @Test
  void calculateAverageDependentsSalaryShouldReturnOneHundredFifty() {
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
  void calculateMaxSalaryShouldReturnZeroIfSalaryZero() {
    // given
    BigDecimal zeroSalary = BigDecimal.ZERO.setScale(DECIMAL_SCALE, ROUNDING_MODE);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateMaxSalary(zeroSalary);

    // then
    assertEquals(zeroSalary, actual);
  }

  @Test
  void calculateMaxSalaryShouldReturnFiftyPercentMore() {
    // given
    BigDecimal salary = new BigDecimal(100).setScale(DECIMAL_SCALE, ROUNDING_MODE);
    BigDecimal expected = new BigDecimal(150).setScale(DECIMAL_SCALE, ROUNDING_MODE);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateMaxSalary(salary);

    // then
    assertEquals(expected, actual);
  }

  @Test
  void calculateMinSalaryShouldReturnZeroIfSalaryZero() {
    // given
    BigDecimal zeroSalary = BigDecimal.ZERO.setScale(DECIMAL_SCALE, ROUNDING_MODE);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateMinSalary(zeroSalary);

    // then
    assertEquals(zeroSalary, actual);
  }

  @Test
  void calculateMinSalaryShouldReturnTwentyPercentMore() {
    // given
    BigDecimal salary = new BigDecimal(100).setScale(DECIMAL_SCALE, ROUNDING_MODE);
    BigDecimal expected = new BigDecimal(120).setScale(DECIMAL_SCALE, ROUNDING_MODE);

    // when
    BigDecimal actual = salaryStatisticCalculator.calculateMinSalary(salary);

    // then
    assertEquals(expected, actual);
  }
}
