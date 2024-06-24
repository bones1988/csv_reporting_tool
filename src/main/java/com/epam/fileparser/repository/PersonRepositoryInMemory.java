package com.epam.fileparser.repository;

import com.epam.fileparser.model.Person;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/** In memory implementation of repository with storing all employees in Map */
public class PersonRepositoryInMemory implements PersonRepository {
  private static final Map<Long, Person> PERSON_STORAGE = new HashMap<>();
  private static final PersonRepositoryInMemory EMPLOYEE_REPOSITORY_IN_MEMORY_INSTANCE =
      new PersonRepositoryInMemory();

  private PersonRepositoryInMemory() {}

  public static PersonRepositoryInMemory getInstance() {
    return EMPLOYEE_REPOSITORY_IN_MEMORY_INSTANCE;
  }

  @Override
  public boolean savePerson(Person person) {
    long personnelId = person.getId();
    if (PERSON_STORAGE.containsKey(personnelId)) {
      return false;
    } else {
      PERSON_STORAGE.put(personnelId, person);
      return true;
    }
  }

  @Override
  public Optional<Person> findById(Long id) {
    return Optional.ofNullable(PERSON_STORAGE.get(id));
  }

  @Override
  public Map<Long, Person> getAllPersons() {
    return Map.copyOf(PERSON_STORAGE);
  }

  @Override
  public void deleteAllEmployees() {
    PERSON_STORAGE.clear();
  }
}
