package com.epam.fileparser.repository;

import com.epam.fileparser.model.Person;
import java.util.Map;
import java.util.Optional;

/**
 * PersonRepository is an interface defining methods that should be implemented by any class
 * designed to perform operations on Person objects.
 */
public interface PersonRepository {
  /**
   * Method to add person to repository
   *
   * @param person Person object that needs to be stored
   * @return boolean if it was stored or not
   */
  boolean savePerson(Person person);

  /**
   * This method should be implemented to retrieve a Person object by its id.
   *
   * @param id - The unique identifier of the Person object to retrieve.
   * @return The Person object associated with the provided id, or null if no such object exists.
   */
  Optional<Person> findById(Long id);

  /**
   * This method should be implemented to retrieve all Person objects from storage.
   *
   * @return map with a key person id and value person object
   */
  Map<Long, Person> getAllPersons();

  /** This method should be implemented to clear all Person objects from storage. */
  void deleteAllEmployees();
}
