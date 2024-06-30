package com.epam.fileparser.person.validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PersonDataValidatorImplTest {
  private final PersonDataValidator personDataValidator = new PersonDataValidatorImpl();
  private static final String VALID_ID_DATA = "1";
  private static final String VALID_FIRST_NAME_DATA = "First";
  private static final String VALID_LAST_NAME_DATA = "Last";
  private static final String VALID_SALARY_DATA = "5";
  private static final String VALID_MANAGER_ID_DATA = "5";

  private static final String INVALID_ID_DATA = "s";
  private static final String INVALID_ID_DATA_BELOW_ZERO = "s";
  private static final String INVALID_FIRST_NAME_DATA = "F";
  private static final String INVALID_LAST_NAME_DATA = "L";
  private static final String INVALID_SALARY_DATA = "s";
  private static final String INVALID_MANAGER_ID_DATA = "d";

  @Test
  void validatePersonDataShouldReturnTrueWhenAllFieldsValidCEO() {
    // given
    String[] validCEOData = {
      VALID_ID_DATA, VALID_FIRST_NAME_DATA, VALID_LAST_NAME_DATA, VALID_SALARY_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(validCEOData);

    // then
    assertTrue(actual);
  }

  @Test
  void validatePersonDataShouldReturnTrueWhenAllFieldsValidEmployee() {
    // given
    String[] validCEOData = {
      VALID_ID_DATA,
      VALID_FIRST_NAME_DATA,
      VALID_LAST_NAME_DATA,
      VALID_SALARY_DATA,
      VALID_MANAGER_ID_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(validCEOData);

    // then
    assertTrue(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithInvalidIdCEO() {
    // given
    String[] inValidCEOData = {
      INVALID_ID_DATA, VALID_FIRST_NAME_DATA, VALID_LAST_NAME_DATA, VALID_SALARY_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(inValidCEOData);

    // then
    assertFalse(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithInvalidIdEmployee() {
    // given
    String[] inValidEmployeeData = {
      INVALID_ID_DATA_BELOW_ZERO,
      VALID_FIRST_NAME_DATA,
      VALID_LAST_NAME_DATA,
      VALID_SALARY_DATA,
      VALID_MANAGER_ID_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(inValidEmployeeData);

    // then
    assertFalse(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithInvalidFirstNameCEO() {
    // given
    String[] inValidCEOData = {
      VALID_ID_DATA, INVALID_FIRST_NAME_DATA, VALID_LAST_NAME_DATA, VALID_SALARY_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(inValidCEOData);

    // then
    assertFalse(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithNullFirstNameEmployee() {
    // given
    String[] inValidEmployeeData = {
      VALID_ID_DATA, null, VALID_LAST_NAME_DATA, VALID_SALARY_DATA, VALID_MANAGER_ID_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(inValidEmployeeData);

    // then
    assertFalse(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithNullLastNameCEO() {
    // given
    String[] inValidCEOData = {VALID_ID_DATA, VALID_FIRST_NAME_DATA, null, VALID_SALARY_DATA};

    // when
    boolean actual = personDataValidator.validatePersonData(inValidCEOData);

    // then
    assertFalse(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithNullLastNameEmployee() {
    // given
    String[] inValidEmployeeData = {
      VALID_ID_DATA,
      VALID_FIRST_NAME_DATA,
      INVALID_LAST_NAME_DATA,
      VALID_SALARY_DATA,
      VALID_MANAGER_ID_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(inValidEmployeeData);

    // then
    assertFalse(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithSalaryCEO() {
    // given
    String[] inValidCEOData = {
      VALID_ID_DATA, VALID_FIRST_NAME_DATA, VALID_LAST_NAME_DATA, INVALID_SALARY_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(inValidCEOData);

    // then
    assertFalse(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithInvalidSalaryEmployee() {
    // given
    String[] inValidEmployeeData = {
      VALID_ID_DATA,
      VALID_FIRST_NAME_DATA,
      VALID_LAST_NAME_DATA,
      INVALID_SALARY_DATA,
      VALID_MANAGER_ID_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(inValidEmployeeData);

    // then
    assertFalse(actual);
  }

  @Test
  void validatePersonDataShouldReturnFalseWithInvalidManagerIdEmployee() {
    // given
    String[] inValidEmployeeData = {
      VALID_ID_DATA,
      VALID_FIRST_NAME_DATA,
      VALID_LAST_NAME_DATA,
      VALID_SALARY_DATA,
      INVALID_MANAGER_ID_DATA
    };

    // when
    boolean actual = personDataValidator.validatePersonData(inValidEmployeeData);

    // then
    assertFalse(actual);
  }
}
