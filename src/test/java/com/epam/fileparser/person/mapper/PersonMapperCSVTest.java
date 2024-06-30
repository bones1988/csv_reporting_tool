package com.epam.fileparser.person.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.message.MessageBuilderMockImplReturnMessage;
import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class PersonMapperCSVTest {
  private final MessageBuilder messageBuilderMock = new MessageBuilderMockImplReturnMessage();
  private final PersonMapper personMapper = new PersonMapperCSV(messageBuilderMock);

  @Test
  void createPersonShouldThrowIllegalArgumentWhenTwoArrays() {
    // given
    String[] inValidEmployeeData = {"1", "2"};

    // then
    assertThrows(
        IllegalArgumentException.class, () -> personMapper.createPerson(inValidEmployeeData));
  }

  @Test
  void createPersonShouldThrowIllegalArgumentWhenSixArrays() {
    // given
    String[] inValidEmployeeData = {"1", "2", "3", "4", "5", "6"};

    // then
    assertThrows(
        IllegalArgumentException.class, () -> personMapper.createPerson(inValidEmployeeData));
  }

  @Test
  void createPersonShouldCreateCEOWhenValidCEODataProvided() {
    // given
    Person ceo = new CEO(1, "CEO", "CEO", new BigDecimal(2));
    String[] validCEOData = {"1", "CEO", "CEO", "2"};

    // when
    Person actual = personMapper.createPerson(validCEOData);

    // then
    assertEquals(ceo, actual);
  }

  @Test
  void createPersonShouldCreateEmployeeWhenValidEmployeeDataProvided() {
    // given
    Person employee = new Employee(1, "CEO", "CEO", new BigDecimal(2), 3L);
    String[] validEmployeeData = {"1", "CEO", "CEO", "2", "3"};

    // when
    Person actual = personMapper.createPerson(validEmployeeData);

    // then
    assertEquals(employee, actual);
  }
}
