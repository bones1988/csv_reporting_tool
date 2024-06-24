package com.epam.fileparser.personutils;

/**
 * PersonDataValidator is a simple interface that represents a validator that is capable of
 * validating employee data coming from file. It requires defining a single method
 * validatePersonData, which takes the array of Strings and returns if the data valid or no.
 */
public interface PersonDataValidator {
  /**
   * Method validatePersonData should take array of Strings and validate it against format
   *
   * @param personalData String array for personal data
   * @return if data is valid
   */
  boolean validatePersonData(String[] personalData);
}
