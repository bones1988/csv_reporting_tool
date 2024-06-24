package com.epam.fileparser.personutils;

import static com.epam.fileparser.runner.ConsoleRunner.DECIMAL_SCALE;
import static com.epam.fileparser.runner.ConsoleRunner.ROUNDING_MODE;

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
  public static final int EMPLOYEE_ID_FIELD_INDEX = 0;
  public static final int EMPLOYEE_FIRST_NAME_FIELD_INDEX = 1;
  public static final int EMPLOYEE_LAST_NAME_FIELD_INDEX = 2;
  public static final int EMPLOYEE_SALARY_FIELD_INDEX = 3;
  public static final int EMPLOYEE_MANAGER_ID_FIELD_INDEX = 4;
  public static final int EMPLOYEE_FIELDS_COUNT = 5;

  private static final int CEO_FIELDS_COUNT = 4;

  @Override
  public Person createPerson(String[] personData) {
    return switch (personData.length) {
      case CEO_FIELDS_COUNT -> createCeo(personData);
      case EMPLOYEE_FIELDS_COUNT -> createEmployee(personData);
      default ->
          throw new IllegalArgumentException(
              "Fields count for person doesn't match with expected. Expected :"
                  + CEO_FIELDS_COUNT
                  + " for CEO and "
                  + EMPLOYEE_FIELDS_COUNT
                  + " for employee. But provided:"
                  + personData.length);
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
