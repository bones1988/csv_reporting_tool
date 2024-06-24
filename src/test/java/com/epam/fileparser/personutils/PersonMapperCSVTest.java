package com.epam.fileparser.personutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class PersonMapperCSVTest {
  private final PersonMapper personMapper = new PersonMapperCSV();

  @Test
  public void createPersonShouldThrowIllegalArgumentWhenTwoArrays() {
    // given
    String[] inValidEmployeeData = {"1", "2"};

    // then
    assertThrows(
        IllegalArgumentException.class, () -> personMapper.createPerson(inValidEmployeeData));
  }

  @Test
  public void createPersonShouldThrowIllegalArgumentWhenSixArrays() {
    // given
    String[] inValidEmployeeData = {"1", "2", "3", "4", "5", "6"};

    // then
    assertThrows(
        IllegalArgumentException.class, () -> personMapper.createPerson(inValidEmployeeData));
  }

  @Test
  public void createPersonShouldCreateCEOWhenValidCEODataProvided() {
    // given
    Person ceo = new CEO(1, "CEO", "CEO", new BigDecimal(2));
    String[] validCEOData = {"1", "CEO", "CEO", "2"};

    // when
    Person actual = personMapper.createPerson(validCEOData);

    // then
    assertEquals(ceo, actual);
  }

  @Test
  public void createPersonShouldCreateEmployeeWhenValidEmployeeDataProvided() {
    // given
    Person employee = new Employee(1, "CEO", "CEO", new BigDecimal(2), 3L);
    String[] validEmployeeData = {"1", "CEO", "CEO", "2", "3"};

    // when
    Person actual = personMapper.createPerson(validEmployeeData);

    // then
    assertEquals(employee, actual);
  }
}
