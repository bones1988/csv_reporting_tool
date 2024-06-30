package com.epam.fileparser.person.validator;

public class PersonDataValidatorMockReturnTrue implements PersonDataValidator {
  @Override
  public boolean validatePersonData(String[] personalData) {
    return true;
  }
}
