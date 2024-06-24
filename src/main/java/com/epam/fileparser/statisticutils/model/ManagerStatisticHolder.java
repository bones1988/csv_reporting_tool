package com.epam.fileparser.statisticutils.model;

import com.epam.fileparser.model.Person;

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
