package com.epam.fileparser.statisticutils.model;

import com.epam.fileparser.model.Person;
import java.math.BigDecimal;

/** Class to store information about salary difference from average dependent salary */
public class SalaryStatisticHolder {
  private Person person;
  private BigDecimal bySalary;

  public void setPerson(Person person) {
    this.person = person;
  }

  public Person getPerson() {
    return this.person;
  }

  public void setBySalary(BigDecimal bySalary) {
    this.bySalary = bySalary;
  }

  public BigDecimal getBySalary() {
    return bySalary;
  }
}
