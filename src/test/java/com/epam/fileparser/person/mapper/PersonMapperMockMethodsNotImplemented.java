package com.epam.fileparser.person.mapper;

import com.epam.fileparser.model.Person;

/** This mock used to check that method was not called */
public class PersonMapperMockMethodsNotImplemented implements PersonMapper {
  @Override
  public Person createPerson(String[] personData) {
    throw new UnsupportedOperationException("createPerson");
  }
}
