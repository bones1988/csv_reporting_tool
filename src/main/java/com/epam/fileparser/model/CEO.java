package com.epam.fileparser.model;

import java.math.BigDecimal;

/** Class for representing CEO */
public class CEO extends Person {
  public CEO(long id, String firstName, String lastName, BigDecimal salary) {
    super(id, firstName, lastName, salary);
  }
}
