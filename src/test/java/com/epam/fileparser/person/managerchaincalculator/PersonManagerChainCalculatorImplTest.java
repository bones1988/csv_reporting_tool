package com.epam.fileparser.person.managerchaincalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.message.MessageBuilderMockImplReturnMessage;
import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class PersonManagerChainCalculatorImplTest {
  private final MessageBuilder messageBuilderMock = new MessageBuilderMockImplReturnMessage();
  private final PersonManagerChainCalculator personManagerChainCalculator =
      new PersonManagerChainCalculatorImpl(messageBuilderMock);

  @Test
  void calculateManagersChainShouldReturnZeroIfEmployeeIsCEO() {
    // given
    Person ceo = new CEO(1, "First", "Last", new BigDecimal(1));
    Person employeeTwo = new Employee(2, "First", "Last", new BigDecimal(100), 1L);
    Map<Long, Person> personMap = new HashMap<>();
    personMap.put(employeeTwo.getId(), employeeTwo);

    // when
    int actual = personManagerChainCalculator.calculateManagersChain(personMap, ceo);

    // then
    assertEquals(0, actual);
  }

  @Test
  void calculateManagersChainShouldReturnTwo() {
    // given
    Person ceo = new CEO(1, "First", "Last", new BigDecimal(1));
    Person employeeTwo = new Employee(2, "First", "Last", new BigDecimal(100), 1L);
    Person employeeThree = new Employee(3, "First", "Last", new BigDecimal(200), 2L);
    Person employeeFour = new Employee(4, "First", "Last", new BigDecimal(100), 3L);
    Map<Long, Person> personMap = new HashMap<>();
    personMap.put(ceo.getId(), ceo);
    personMap.put(employeeTwo.getId(), employeeTwo);
    personMap.put(employeeThree.getId(), employeeThree);
    personMap.put(employeeFour.getId(), employeeFour);

    // when
    int actual = personManagerChainCalculator.calculateManagersChain(personMap, employeeFour);

    // then
    assertEquals(2, actual);
  }

  @Test
  void calculateManagersChainShouldThrowExceptionWhenManagersAreCircled() {
    // given
    Person ceo = new CEO(1, "First", "Last", new BigDecimal(1));
    Person employeeTwo = new Employee(2, "First", "Last", new BigDecimal(100), 3L);
    Person employeeThree = new Employee(3, "First", "Last", new BigDecimal(200), 2L);
    Person employeeFour = new Employee(4, "First", "Last", new BigDecimal(100), 3L);
    Map<Long, Person> personMap = new HashMap<>();
    personMap.put(ceo.getId(), ceo);
    personMap.put(employeeTwo.getId(), employeeTwo);
    personMap.put(employeeThree.getId(), employeeThree);
    personMap.put(employeeFour.getId(), employeeFour);

    // then
    assertThrows(
        IllegalArgumentException.class,
        () -> personManagerChainCalculator.calculateManagersChain(personMap, employeeFour));
  }

  @Test
  void calculateManagersChainShouldThrowExceptionWhenManagerIsAbsentInTheMap() {
    // given
    Person ceo = new CEO(1, "First", "Last", new BigDecimal(1));
    Person employeeTwo = new Employee(2, "First", "Last", new BigDecimal(100), 3L);
    Person employeeThree = new Employee(3, "First", "Last", new BigDecimal(200), 12L);
    Person employeeFour = new Employee(4, "First", "Last", new BigDecimal(100), 3L);
    Map<Long, Person> personMap = new HashMap<>();
    personMap.put(ceo.getId(), ceo);
    personMap.put(employeeTwo.getId(), employeeTwo);
    personMap.put(employeeThree.getId(), employeeThree);
    personMap.put(employeeFour.getId(), employeeFour);

    // then
    assertThrows(
        IllegalArgumentException.class,
        () -> personManagerChainCalculator.calculateManagersChain(personMap, employeeFour));
  }
}
