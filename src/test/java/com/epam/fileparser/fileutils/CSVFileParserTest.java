package com.epam.fileparser.fileutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import com.epam.fileparser.personutils.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CSVFileParserTest {
  private final PersonMapper personMapper = new PersonMapperMockMethodsNotImplemented();
  private final PersonDataValidator personDataValidator =
      new PersonDataValidatorMockMethodsNotImplemented();
  private final FileParser fileParser = new CSVFileParser(personMapper, personDataValidator);

  @Test
  public void parseFileShouldThrowIllegalArgumentExceptionWhenFilePathIsNotCSV() {
    // given
    String incorrectFilePath = "sss.doc";

    // then
    assertThrows(IllegalArgumentException.class, () -> fileParser.parseFile(incorrectFilePath));
  }

  @Test
  public void parseFileShouldThrowNoSuchFileExceptionWhenThereIsNoSuchFileButWithCSVExtension() {
    // given
    String notExistingFilePath = "no_such_file.csv";
    // then
    assertThrows(NoSuchFileException.class, () -> fileParser.parseFile(notExistingFilePath));
  }

  @Test
  public void parseFileShouldReturnEmptyMapIfFileIsEmpty() throws URISyntaxException, IOException {
    // given
    URL res = getClass().getClassLoader().getResource("empty.csv");
    String emptyFilePath = Paths.get(res.toURI()).toString();

    // when
    Map<Long, Person> actual = fileParser.parseFile(emptyFilePath);

    // then
    assertTrue(actual.isEmpty());
  }

  @Test
  public void parseFileShouldThrowIllegalArgumentExceptionWhenHasMoreLinesThanAllowed()
      throws URISyntaxException {
    // given
    URL res = getClass().getClassLoader().getResource("too_long.csv");
    String longFilePath = Paths.get(res.toURI()).toString();

    // then
    assertThrows(IllegalArgumentException.class, () -> fileParser.parseFile(longFilePath));
  }

  @Test
  public void parseFileShouldThrowIllegalArgumentExceptionWhenHasMoreThanOneEmployeeWithSameId()
      throws URISyntaxException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnTrue();
    PersonMapper personMapperMock = new PersonMapperMockReturnSameIdEmployee();
    FileParser mockedFileParser = new CSVFileParser(personMapperMock, dataValidatorMock);
    URL res = getClass().getClassLoader().getResource("same_id.csv");
    String longFilePath = Paths.get(res.toURI()).toString();

    // when

    // then
    assertThrows(IllegalArgumentException.class, () -> mockedFileParser.parseFile(longFilePath));
  }

  @Test
  public void parseFileShouldReturnMapWithThreeEmployeesWithCorrectData()
      throws URISyntaxException, IOException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnTrue();
    PersonMapper personMapperMock = new PersonMapperMockReturnThreeEmployeeForProvidedData();
    FileParser mockedFileParser = new CSVFileParser(personMapperMock, dataValidatorMock);
    URL res = getClass().getClassLoader().getResource("correct_file.csv");
    String correctFilePath = Paths.get(res.toURI()).toString();
    Person personOne = new CEO(123, "Joe", "Doe", new BigDecimal(60000));
    Person personTwo = new Employee(124, "Anton", "Chekov", new BigDecimal(45000), 123L);
    Person personThree = new Employee(125, "Martin", "Mart", new BigDecimal(45000), 123L);

    // when
    Map<Long, Person> actual = mockedFileParser.parseFile(correctFilePath);
    Person actualPersonOne = actual.get(123L);
    Person actualPersonTwo = actual.get(124L);
    Person actualPersonThree = actual.get(125L);

    // then
    assertEquals(3, actual.size());
    assertEquals(personOne, actualPersonOne);
    assertEquals(personTwo, actualPersonTwo);
    assertEquals(personThree, actualPersonThree);
  }

  @Test
  public void parseFileShouldThrowIllegalArgumentExceptionWhenDataInvalid()
      throws URISyntaxException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnFalse();
    PersonMapper personMapperMock = new PersonMapperMockMethodsNotImplemented();
    FileParser mockedFileParser = new CSVFileParser(personMapperMock, dataValidatorMock);
    URL res = getClass().getClassLoader().getResource("correct_file.csv");
    String correctFilePath = Paths.get(res.toURI()).toString();

    // then
    assertThrows(IllegalArgumentException.class, () -> mockedFileParser.parseFile(correctFilePath));
  }

  @Test
  public void parseFileShouldThrowIllegalArgumentExceptionWhenMoreThanOneCEO()
      throws URISyntaxException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnFalse();
    PersonMapper personMapperMock = new PersonMapperMockMethodsNotImplemented();
    FileParser mockedFileParser = new CSVFileParser(personMapperMock, dataValidatorMock);
    URL res = getClass().getClassLoader().getResource("two_CEO.csv");
    String twoCEOPath = Paths.get(res.toURI()).toString();

    // then
    assertThrows(IllegalArgumentException.class, () -> mockedFileParser.parseFile(twoCEOPath));
  }
}
