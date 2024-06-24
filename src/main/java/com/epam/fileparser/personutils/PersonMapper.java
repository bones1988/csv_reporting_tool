package com.epam.fileparser.personutils;

import com.epam.fileparser.model.Person;

/**
 * PersonMapper is an interface that defines a contract for a mapper that turns raw person data into
 * a Person object.
 */
public interface PersonMapper {

  /**
   * This method takes in an array of strings containing personData, which it transforms to a Person
   * object.
   *
   * @param personData - An array of strings where each string represents a field of the Person
   *     object.
   * @return Person object created from the supplied data array.
   */
  Person createPerson(String[] personData);
}
