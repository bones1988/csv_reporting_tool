package com.epam.fileparser.personutils;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.util.*;

public class PersonManagerChainCalculatorImpl implements PersonManagerChainCalculator {

  @Override
  public int calculateManagersChain(Map<Long, Person> personsMap, Person person) {
    Set<Long> managerIds = new HashSet<>();
    if (person instanceof CEO) {
      return 0;
    }
    if (person instanceof Employee employee) {
      Long managerId = employee.getManagerId();

      while (managerId != null) {
        Person manager = personsMap.get(managerId);
        if (manager == null) {
          throw new IllegalArgumentException(
              "Employee has manager Id which is not in employee list. Id:" + managerId);
        }
        if (manager instanceof CEO) {
          break;
        }
        boolean added = managerIds.add(manager.getId());
        if (!added) {
          throw new IllegalArgumentException(
              "Employee has circled managers chain. Id:" + person.getId());
        }
        if (manager instanceof Employee employeeManager) {
          managerId = employeeManager.getManagerId();
        }
      }
    }
    return managerIds.size();
  }
}
