package com.epam.fileparser.personutils;

import com.epam.fileparser.model.Person;
import java.util.Map;

/** This mock used to check that method was not called */
public class PersonManagerChainCalculatorMockMethodsNotImplemented
    implements PersonManagerChainCalculator {
  @Override
  public int calculateManagersChain(Map<Long, Person> personsMap, Person person) {
    throw new UnsupportedOperationException("calculateManagersChain");
  }
}
