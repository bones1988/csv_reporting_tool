package com.epam.fileparser.personutils;

public class PersonDataValidatorMockReturnTrue implements PersonDataValidator {
  @Override
  public boolean validatePersonData(String[] personalData) {
    return true;
  }
}
