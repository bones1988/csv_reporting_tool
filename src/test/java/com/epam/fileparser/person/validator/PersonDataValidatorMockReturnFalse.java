package com.epam.fileparser.person.validator;

public class PersonDataValidatorMockReturnFalse implements PersonDataValidator {
  @Override
  public boolean validatePersonData(String[] personalData) {
    return false;
  }
}
