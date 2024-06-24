package com.epam.fileparser.personutils;

import com.epam.fileparser.model.Person;
import java.util.Map;

/**
 * PersonManagerChainCalculator is an interface defining a contract for a calculator capable of
 * calculating the length of the manager chain for a person within a group of persons.
 */
public interface PersonManagerChainCalculator {
  /**
   * This method calculates and returns the length of the manager chain for a specific person within
   * a map of persons.
   *
   * @param personsMap - A map of persons where the key is the person's id, and the value is the
   *     person object
   * @param person - The person for whom the manager chain length will be calculated
   * @return The length of the manager chain for the given person
   */
  int calculateManagersChain(Map<Long, Person> personsMap, Person person);
}
