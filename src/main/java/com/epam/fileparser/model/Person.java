package com.epam.fileparser.model;

import static com.epam.fileparser.constants.ApplicationConstants.*;

import com.epam.fileparser.message.MessageBuilder;
import java.math.BigDecimal;
import java.util.Objects;

/** Abstract class for representing abstract user of system */
public abstract class Person {
  public static final String PERSON_TO_STRING_MESSAGE_KEY = "person_string_representation";

  private final long id;
  private final String firstName;
  private final String lastName;
  private final BigDecimal salary;

  Person(long id, String firstName, String lastName, BigDecimal salary) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary.setScale(DECIMAL_SCALE, ROUNDING_MODE);
  }

  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Person person)) {
      return false;
    }
    return id == person.id
        && Objects.equals(firstName, person.firstName)
        && Objects.equals(lastName, person.lastName)
        && Objects.equals(salary, person.salary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, salary);
  }

  public String getStringOutput(MessageBuilder messageBuilder) {
    return messageBuilder.buildMessage(
        PERSON_TO_STRING_MESSAGE_KEY, id, firstName, lastName, salary);
  }
}
