package com.epam.fileparser.person.mapper;

import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;

/** Always returns same employee */
public class PersonMapperMockReturnSameIdEmployee implements PersonMapper {
  @Override
  public Person createPerson(String[] personData) {
    return new Employee(1, "First", "Last", new BigDecimal(2), 3L);
  }
}
