package com.epam.fileparser.person.mapper;

import static com.epam.fileparser.constants.ApplicationConstants.*;

import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;

/**
 * PersonMapperCSV provides a concrete implementation of the PersonMapper interface for CSV-based
 * data. It takes an array of Strings from a CSV record as input and produces a corresponding Person
 * object.
 */
public class PersonMapperCSV implements PersonMapper {
  private static final int CEO_FIELDS_COUNT = 4;

  private static final String FIELDS_COUNT_ERROR_MESSAGE_KEY = "fields_count_error";

  private final MessageBuilder messageBuilder;

  public PersonMapperCSV(MessageBuilder messageBuilder) {
    this.messageBuilder = messageBuilder;
  }

  @Override
  public Person createPerson(String[] personData) {
    return switch (personData.length) {
      case CEO_FIELDS_COUNT -> createCeo(personData);
      case EMPLOYEE_FIELDS_COUNT -> createEmployee(personData);
      default ->
          throw new IllegalArgumentException(
              messageBuilder.buildMessage(
                  FIELDS_COUNT_ERROR_MESSAGE_KEY,
                  CEO_FIELDS_COUNT,
                  EMPLOYEE_FIELDS_COUNT,
                  personData.length));
    };
  }

  private CEO createCeo(String[] personData) {
    long id = getIdFromPersonData(personData);
    String firstName = getFirstNameFromPersonData(personData);
    String lastName = getLastNameFromPersonData(personData);
    BigDecimal salary = getSalaryFromPersonData(personData);
    return new CEO(id, firstName, lastName, salary);
  }

  private Employee createEmployee(String[] personData) {
    long id = getIdFromPersonData(personData);
    String firstName = getFirstNameFromPersonData(personData);
    String lastName = getLastNameFromPersonData(personData);
    BigDecimal salary = getSalaryFromPersonData(personData);
    Long managerId = getManagerIdFromPersonData(personData);
    return new Employee(id, firstName, lastName, salary, managerId);
  }

  private long getIdFromPersonData(String[] personData) {
    return Long.parseLong(personData[EMPLOYEE_ID_FIELD_INDEX]);
  }

  private String getFirstNameFromPersonData(String[] personData) {
    return personData[EMPLOYEE_FIRST_NAME_FIELD_INDEX];
  }

  private String getLastNameFromPersonData(String[] personData) {
    return personData[EMPLOYEE_LAST_NAME_FIELD_INDEX];
  }

  private BigDecimal getSalaryFromPersonData(String[] personData) {
    return new BigDecimal(personData[EMPLOYEE_SALARY_FIELD_INDEX])
        .setScale(DECIMAL_SCALE, ROUNDING_MODE);
  }

  private Long getManagerIdFromPersonData(String[] personData) {
    return Long.parseLong(personData[EMPLOYEE_MANAGER_ID_FIELD_INDEX]);
  }
}
