package com.epam.fileparser.personutils;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;

/** return 3 different employees based on id provided in the first position of data array */
public class PersonMapperMockReturnThreeEmployeeForProvidedData implements PersonMapper {
  @Override
  public Person createPerson(String[] personData) {
    return switch (personData[0]) {
      case "123" -> new CEO(123, "Joe", "Doe", new BigDecimal(60000));
      case "124" -> new Employee(124, "Anton", "Chekov", new BigDecimal(45000), 123L);
      case "125" -> new Employee(125, "Martin", "Mart", new BigDecimal(45000), 123L);
      default -> throw new IllegalArgumentException("Unexpected data provided");
    };
  }
}
