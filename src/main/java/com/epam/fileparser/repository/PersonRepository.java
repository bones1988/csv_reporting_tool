package com.epam.fileparser.repository;

import com.epam.fileparser.model.Person;

import java.util.Map;
import java.util.Optional;

public interface PersonRepository {
    boolean savePerson(Person person);
    Optional<Person> getById(Long id);
    Map<Long, Person> getAllPersons();
    void deleteAllEmployees();
}
