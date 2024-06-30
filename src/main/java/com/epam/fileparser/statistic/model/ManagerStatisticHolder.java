package com.epam.fileparser.statistic.model;

import com.epam.fileparser.model.Person;

/** Class to store statistic about manager chain */
public class ManagerStatisticHolder {
  private Person person;
  private int byNumber;

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public int getByNumber() {
    return byNumber;
  }

  public void setByNumber(int byNumber) {
    this.byNumber = byNumber;
  }
}
