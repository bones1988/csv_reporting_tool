package com.epam.fileparser.person.managerchaincalculator;

import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.util.*;

/**
 * PersonManagerChainCalculatorImpl is a concrete implementation of the PersonManagerChainCalculator
 * interface. It provides the functionality to calculate the length of the manager chain for a
 * specific person within a map of persons.
 */
public class PersonManagerChainCalculatorImpl implements PersonManagerChainCalculator {
  private static final int CEO_MANAGERS_LIST_SIZE = 0;
  private static final String MANAGER_ID_NOT_IN_LIST_MESSAGE_KEY = "manager_id_not_in_list";
  private static final String CIRCLED_MANAGERS_CHAIN_MESSAGE_KEY = "circled_managers_chain";

  private final MessageBuilder messageBuilder;

  public PersonManagerChainCalculatorImpl(MessageBuilder messageBuilder) {
    this.messageBuilder = messageBuilder;
  }

  @Override
  public int calculateManagersChain(Map<Long, Person> personsMap, Person person) {
    Set<Long> managerIds = new HashSet<>();
    if (person instanceof CEO) {
      return CEO_MANAGERS_LIST_SIZE;
    }
    if (person instanceof Employee employee) {
      Long managerId = employee.getManagerId();

      while (managerId != null) {
        Person manager = getValidManager(personsMap, managerId);
        if (manager instanceof CEO) {
          break;
        }
        long personId = person.getId();
        updateManagerChain(managerIds, personId, managerId);

        managerId = getNextManagerId(manager);
      }
    }
    return managerIds.size();
  }

  /**
   * Fetches the manager from the personsMap using their ID. Throws an IllegalArgumentException if
   * the manager is not found that means that or personMap is invalid or employee has invalid
   * manager id.
   *
   * @param personsMap map of persons by their Ids
   * @param managerId the id of the manager to be fetched
   * @return the Person object representing the manager
   */
  private Person getValidManager(Map<Long, Person> personsMap, long managerId) {
    Person manager = personsMap.get(managerId);
    if (manager == null) {
      throw new IllegalArgumentException(
          messageBuilder.buildMessage(MANAGER_ID_NOT_IN_LIST_MESSAGE_KEY, managerId));
    }
    return manager;
  }

  /**
   * Adds the managerId to the managerIds set. Throws an IllegalArgumentException if the managerId
   * is already present in the set indicating a circular managerial hierarchy (that means we cannot
   * build reporting line to the CEO).
   *
   * @param managerIds the set of manager Ids
   * @param personId the id of the person for whom the manager chain is being constructed
   * @param managerId the manager id to be added to the manager chain set
   */
  private void updateManagerChain(Set<Long> managerIds, long personId, long managerId) {
    boolean added = managerIds.add(managerId);
    if (!added) {
      throw new IllegalArgumentException(
          messageBuilder.buildMessage(CIRCLED_MANAGERS_CHAIN_MESSAGE_KEY, personId));
    }
  }

  private Long getNextManagerId(Person manager) {
    return manager instanceof Employee employee ? employee.getManagerId() : null;
  }
}
