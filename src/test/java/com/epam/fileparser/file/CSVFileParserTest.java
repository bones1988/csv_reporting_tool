package com.epam.fileparser.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.message.MessageBuilderMockImplReturnMessage;
import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Employee;
import com.epam.fileparser.model.Person;
import com.epam.fileparser.person.mapper.*;
import com.epam.fileparser.person.validator.PersonDataValidator;
import com.epam.fileparser.person.validator.PersonDataValidatorMockMethodsNotImplemented;
import com.epam.fileparser.person.validator.PersonDataValidatorMockReturnFalse;
import com.epam.fileparser.person.validator.PersonDataValidatorMockReturnTrue;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Map;
import org.junit.jupiter.api.Test;

class CSVFileParserTest {
  private final PersonMapper personMapper = new PersonMapperMockMethodsNotImplemented();
  private final PersonDataValidator personDataValidator =
      new PersonDataValidatorMockMethodsNotImplemented();
  private final MessageBuilder messageBuilderMock = new MessageBuilderMockImplReturnMessage();
  private final FileParser fileParser =
      new CSVFileParser(personMapper, personDataValidator, messageBuilderMock);

  @Test
  void parseFileShouldThrowIllegalArgumentExceptionWhenFilePathIsNotCSV() {
    // given
    String incorrectFilePath = "sss.doc";

    // then
    assertThrows(IllegalArgumentException.class, () -> fileParser.parseFile(incorrectFilePath));
  }

  @Test
  void parseFileShouldThrowNoSuchFileExceptionWhenThereIsNoSuchFileButWithCSVExtension() {
    // given
    String notExistingFilePath = "no_such_file.csv";
    // then
    assertThrows(NoSuchFileException.class, () -> fileParser.parseFile(notExistingFilePath));
  }

  @Test
  void parseFileShouldReturnEmptyMapIfFileIsEmpty() throws URISyntaxException, IOException {
    // given
    URL res = getClass().getClassLoader().getResource("empty.csv");
    String emptyFilePath = Paths.get(res.toURI()).toString();

    // when
    Map<Long, Person> actual = fileParser.parseFile(emptyFilePath);

    // then
    assertTrue(actual.isEmpty());
  }

  @Test
  void parseFileShouldThrowIllegalArgumentExceptionWhenHasMoreLinesThanAllowed()
      throws URISyntaxException {
    // given
    URL res = getClass().getClassLoader().getResource("too_long.csv");
    String longFilePath = Paths.get(res.toURI()).toString();

    // then
    assertThrows(IllegalArgumentException.class, () -> fileParser.parseFile(longFilePath));
  }

  @Test
  void parseFileShouldThrowIllegalArgumentExceptionWhenHasMoreThanOneEmployeeWithSameId()
      throws URISyntaxException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnTrue();
    PersonMapper personMapperMock = new PersonMapperMockReturnSameIdEmployee();
    FileParser mockedFileParser =
        new CSVFileParser(personMapperMock, dataValidatorMock, messageBuilderMock);
    URL res = getClass().getClassLoader().getResource("same_id.csv");
    String longFilePath = Paths.get(res.toURI()).toString();

    // then
    assertThrows(IllegalArgumentException.class, () -> mockedFileParser.parseFile(longFilePath));
  }

  @Test
  void parseFileShouldThrowIllegalArgumentExceptionWhenHasMoreThanOneCEO()
      throws URISyntaxException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnTrue();
    PersonMapper personMapperMock = new PersonMapperMockReturnCEO();
    FileParser mockedFileParser =
        new CSVFileParser(personMapperMock, dataValidatorMock, messageBuilderMock);
    URL res = getClass().getClassLoader().getResource("same_id.csv");
    String longFilePath = Paths.get(res.toURI()).toString();

    // then
    assertThrows(IllegalArgumentException.class, () -> mockedFileParser.parseFile(longFilePath));
  }

  @Test
  void parseFileShouldReturnMapWithThreeEmployeesWithCorrectData()
      throws URISyntaxException, IOException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnTrue();
    PersonMapper personMapperMock = new PersonMapperMockReturnThreeEmployeeForProvidedData();
    FileParser mockedFileParser =
        new CSVFileParser(personMapperMock, dataValidatorMock, messageBuilderMock);
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
  void parseFileShouldThrowIllegalArgumentExceptionWhenDataInvalid() throws URISyntaxException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnFalse();
    PersonMapper personMapperMock = new PersonMapperMockMethodsNotImplemented();
    FileParser mockedFileParser =
        new CSVFileParser(personMapperMock, dataValidatorMock, messageBuilderMock);
    URL res = getClass().getClassLoader().getResource("correct_file.csv");
    String correctFilePath = Paths.get(res.toURI()).toString();

    // then
    assertThrows(IllegalArgumentException.class, () -> mockedFileParser.parseFile(correctFilePath));
  }

  @Test
  void parseFileShouldThrowIllegalArgumentExceptionWhenMoreThanOneCEO() throws URISyntaxException {
    // given
    PersonDataValidator dataValidatorMock = new PersonDataValidatorMockReturnFalse();
    PersonMapper personMapperMock = new PersonMapperMockMethodsNotImplemented();
    FileParser mockedFileParser =
        new CSVFileParser(personMapperMock, dataValidatorMock, messageBuilderMock);
    URL res = getClass().getClassLoader().getResource("two_CEO.csv");
    String twoCEOPath = Paths.get(res.toURI()).toString();

    // then
    assertThrows(IllegalArgumentException.class, () -> mockedFileParser.parseFile(twoCEOPath));
  }
}
