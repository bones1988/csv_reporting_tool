package com.epam.fileparser.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Employee extends Person {
  private final Long managerId;

  public Employee(long id, String firstName, String lastName, BigDecimal salary, Long managerId) {
    super(id, firstName, lastName, salary);
    this.managerId = managerId;
  }

  public Long getManagerId() {
    return managerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Employee employee = (Employee) o;
    return Objects.equals(managerId, employee.managerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), managerId);
  }

  @Override
  public String toString() {
    return super.toString() + " Manager Id:" + managerId;
  }
}
