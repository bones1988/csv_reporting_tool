package com.epam.fileparser.repository;

import com.epam.fileparser.model.Person;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/** Returns empty map for getAllPersons and throws exception for other methods */
public class PersonRepositoryMockReturnEmptyMap implements PersonRepository {
  @Override
  public boolean savePerson(Person person) {
    throw new UnsupportedOperationException("savePerson");
  }

  @Override
  public Optional<Person> findById(Long id) {
    throw new UnsupportedOperationException("findById");
  }

  @Override
  public Map<Long, Person> getAllPersons() {
    return Collections.emptyMap();
  }

  @Override
  public void deleteAllEmployees() {
    throw new UnsupportedOperationException("deleteAllEmployees");
  }
}
