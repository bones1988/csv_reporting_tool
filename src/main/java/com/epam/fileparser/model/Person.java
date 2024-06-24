package com.epam.fileparser.model;

import static com.epam.fileparser.runner.ConsoleRunner.DECIMAL_SCALE;
import static com.epam.fileparser.runner.ConsoleRunner.ROUNDING_MODE;

import java.math.BigDecimal;
import java.util.Objects;

/** Abstract class for representing abstract user of system */
public abstract class Person {
  private final long id;
  private final String firstName;
  private final String lastName;
  private final BigDecimal salary;

  public Person(long id, String firstName, String lastName, BigDecimal salary) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = salary.setScale(DECIMAL_SCALE, ROUNDING_MODE);
  }

  public long getId() {
    return id;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return id == person.id
        && Objects.equals(firstName, person.firstName)
        && Objects.equals(lastName, person.lastName)
        && Objects.equals(salary, person.salary);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, salary);
  }

  @Override
  public String toString() {
    return "Id: "
        + id
        + " FirstName: "
        + firstName
        + " LastName: "
        + lastName
        + " Salary: "
        + salary;
  }
}
