package com.epam.fileparser.person.mapper;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;

/** Always returns same CEO */
public class PersonMapperMockReturnCEO implements PersonMapper {
  @Override
  public Person createPerson(String[] personData) {
    return new CEO(1, "First", "Last", new BigDecimal(2));
  }
}
