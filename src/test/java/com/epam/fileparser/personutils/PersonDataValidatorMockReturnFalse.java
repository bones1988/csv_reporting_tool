package com.epam.fileparser.personutils;

public class PersonDataValidatorMockReturnFalse implements PersonDataValidator {
  @Override
  public boolean validatePersonData(String[] personalData) {
    return false;
  }
}
