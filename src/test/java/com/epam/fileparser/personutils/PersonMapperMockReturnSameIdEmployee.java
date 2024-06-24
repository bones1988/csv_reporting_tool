package com.epam.fileparser.personutils;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;

/** Always returns same employee */
public class PersonMapperMockReturnSameIdEmployee implements PersonMapper {
  @Override
  public Person createPerson(String[] personData) {
    return new CEO(1, "First", "Last", new BigDecimal(2));
  }
}
