package com.epam.fileparser.person.validator;

/** This mock implementation is used to make sure that method was not called */
public class PersonDataValidatorMockMethodsNotImplemented implements PersonDataValidator {
  @Override
  public boolean validatePersonData(String[] personalData) {
    throw new UnsupportedOperationException("validatePersonData");
  }
}
