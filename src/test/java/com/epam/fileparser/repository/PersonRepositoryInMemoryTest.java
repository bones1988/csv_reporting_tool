package com.epam.fileparser.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonRepositoryInMemoryTest {
  private final PersonRepository personRepository = PersonRepositoryInMemory.getInstance();
  private Person employeeOne;

  @BeforeEach
  public void setup() {
    personRepository.deleteAllEmployees();
    employeeOne = new Employee(1, "First", "Last", new BigDecimal(1), 1L);
  }

  @Test
  public void saveEmployeeShouldAddEmployeeToEmptyStorage() {
    // when
    assertTrue(personRepository.getAllPersons().isEmpty());
    boolean actual = personRepository.savePerson(employeeOne);

    // then
    assertTrue(actual);
    assertEquals(1, personRepository.getAllPersons().size());
    assertEquals(employeeOne, personRepository.getById(1L).get());
  }

  @Test
  public void saveEmployeeShouldAddUniqueEmployeeToStorage() {
    // given
    Person employeeTwo = new Employee(2, "Second", "SecondLast", new BigDecimal(2), 2L);
    Person employeeThree = new Employee(3, "Third", "ThirdLast", new BigDecimal(3), 3L);

    // when
    assertTrue(personRepository.savePerson(employeeOne));
    assertTrue(personRepository.savePerson(employeeTwo));
    assertEquals(2, personRepository.getAllPersons().size());
    assertEquals(employeeOne, personRepository.getById(1L).get());
    assertEquals(employeeTwo, personRepository.getById(2L).get());
    boolean actual = personRepository.savePerson(employeeThree);

    // then
    assertTrue(actual);
    assertEquals(3, personRepository.getAllPersons().size());
    assertEquals(employeeThree, personRepository.getById(3L).get());
  }

  @Test
  public void saveEmployeeShouldNotAddEmployeeWithSameIdToStorageAndShouldNotReplacePrevious() {
    // given
    Person employeeTwo = new Employee(2, "Second", "SecondLast", new BigDecimal(2), 2L);
    Person employeeThree = new Employee(1, "Third", "ThirdLast", new BigDecimal(3), 3L);

    // when
    assertTrue(personRepository.savePerson(employeeOne));
    assertTrue(personRepository.savePerson(employeeTwo));
    assertEquals(2, personRepository.getAllPersons().size());
    assertEquals(employeeOne, personRepository.getById(1L).get());
    assertEquals(employeeTwo, personRepository.getById(2L).get());
    boolean actual = personRepository.savePerson(employeeThree);

    // then
    assertFalse(actual);
    assertEquals(2, personRepository.getAllPersons().size());
    assertEquals(employeeOne, personRepository.getById(1L).get());
  }

  @Test
  public void getByIdShouldReturnOptionalWithPersonWhenThereIsPersonWithProvidedId() {
    // when
    assertTrue(personRepository.savePerson(employeeOne));
    assertEquals(1, personRepository.getAllPersons().size());
    Optional<Person> actual = personRepository.getById(1L);

    // then
    assertTrue(actual.isPresent());
    Person actualPerson = actual.get();
    assertEquals(employeeOne, actualPerson);
  }

  @Test
  public void getByIdShouldReturnEmptyOptionalWhenThereIsNoPersonWithProvidedId() {
    // when
    assertTrue(personRepository.savePerson(employeeOne));
    assertEquals(1, personRepository.getAllPersons().size());
    Optional<Person> actual = personRepository.getById(2L);

    // then
    assertTrue(actual.isEmpty());
  }

  @Test
  public void getAllPersonsShouldReturnEmptyMapWhenThereIsNoPersonInRepository() {
    // when
    Map<Long, Person> actual = personRepository.getAllPersons();

    // then
    assertTrue(actual.isEmpty());
  }

  @Test
  public void getByIdShouldReturnMapWithPersonWhenThereIsAPersonInRepository() {
    // when
    assertTrue(personRepository.savePerson(employeeOne));
    Map<Long, Person> actual = personRepository.getAllPersons();

    // then
    assertEquals(1, actual.size());
    assertEquals(employeeOne, actual.get(1L));
  }
}