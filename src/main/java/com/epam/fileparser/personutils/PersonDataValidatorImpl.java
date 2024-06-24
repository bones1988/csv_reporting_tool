package com.epam.fileparser.personutils;

import static com.epam.fileparser.personutils.PersonMapperCSV.*;

import java.math.BigDecimal;

public class PersonDataValidatorImpl implements PersonDataValidator {
  private static final int MIN_SIGNS_FOR_NAME_LAST_NAME = 2;

  @Override
  public boolean validatePersonData(String[] personalData) {
    String idData = personalData[EMPLOYEE_ID_FIELD_INDEX];
    boolean isIdValid = validateId(idData);

    String firstName = personalData[EMPLOYEE_FIRST_NAME_FIELD_INDEX];
    boolean isFirstNameValid = validateFirstNameLastName(firstName);

    String lastName = personalData[EMPLOYEE_LAST_NAME_FIELD_INDEX];
    boolean isLastNameValid = validateFirstNameLastName(lastName);

    String salaryData = personalData[EMPLOYEE_SALARY_FIELD_INDEX];
    boolean isSalaryValid = validateSalary(salaryData);

    boolean isManagerIdValid = true;
    if (personalData.length == EMPLOYEE_FIELDS_COUNT) {
      String managerIdData = personalData[EMPLOYEE_MANAGER_ID_FIELD_INDEX];
      isManagerIdValid = validateId(managerIdData);
    }

    return isIdValid && isFirstNameValid && isLastNameValid && isSalaryValid && isManagerIdValid;
  }

  private boolean validateId(String idData) {
    long id;
    try {
      id = Long.parseLong(idData);
    } catch (NumberFormatException e) {
      return false;
    }
    return id > 0;
  }

  private boolean validateFirstNameLastName(String name) {
    if(name == null) {
      return false;
    }
    return name.length() >= MIN_SIGNS_FOR_NAME_LAST_NAME;
  }

  private boolean validateSalary(String salaryData) {
    BigDecimal salary;
    try {
      salary = new BigDecimal(salaryData);
    } catch (NumberFormatException e) {
      return false;
    }

    return salary.compareTo(BigDecimal.ZERO) > 0;
  }
}
