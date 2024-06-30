package com.epam.fileparser.model;

import com.epam.fileparser.message.MessageBuilder;
import java.math.BigDecimal;
import java.util.Objects;

/** Class for representing employee */
public class Employee extends Person {
  public static final String MANAGER_ID_MESSAGE_KEY = "manager_id";

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

    if (!(o instanceof Employee employee)) {
      return false;
    }

    return Objects.equals(getId(), employee.getId())
        && Objects.equals(getFirstName(), employee.getFirstName())
        && Objects.equals(getLastName(), employee.getLastName())
        && Objects.equals(getSalary(), employee.getSalary())
        && Objects.equals(managerId, employee.managerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), managerId);
  }

  @Override
  public String getStringOutput(MessageBuilder messageBuilder) {
    return super.getStringOutput(messageBuilder)
        + " "
        + messageBuilder.buildMessage(MANAGER_ID_MESSAGE_KEY, managerId);
  }
}
