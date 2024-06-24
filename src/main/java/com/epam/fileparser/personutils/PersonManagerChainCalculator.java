package com.epam.fileparser.personutils;

import com.epam.fileparser.model.Person;

import java.util.Map;

public interface PersonManagerChainCalculator {
  int calculateManagersChain(Map<Long, Person> personsMap, Person person);
}
