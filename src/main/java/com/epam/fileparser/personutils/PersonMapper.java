package com.epam.fileparser.personutils;

import com.epam.fileparser.model.Person;

public interface PersonMapper {
  Person createPerson(String[] personData);
}
