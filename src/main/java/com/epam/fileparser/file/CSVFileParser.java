package com.epam.fileparser.file;

import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Person;
import com.epam.fileparser.person.mapper.PersonMapper;
import com.epam.fileparser.person.validator.PersonDataValidator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * CSVFileParser is a class that implements the parsing functionality defined in the FileParser
 * interface. It parses CSV files to map representation where key is an id of the Person, and value
 * is a Person object.
 */
public class CSVFileParser implements FileParser {
  private static final String COLUMN_DELIMITER = ",";
  private static final int FILE_HEADER_COLUMNS_NUMBER = 1;
  private static final int MAX_LINES = 1000;
  private static final String CSV_FILE_EXTENSION = ".csv";
  private static final String EXTENSION_SHOULD_BE_CSV_MESSAGE_KEY = "extension_should_be_csv";
  private static final String MORE_LINES_MESSAGE_KEY = "more_lines";
  private static final String INCORRECT_DATA_FOR_LINE_MESSAGE_KEY = "incorrect_data_for_line";
  private static final String MORE_THAN_ONE_CEO_MESSAGE_KEY = "more_than_one_ceo";
  private static final String CANNOT_CONTAIN_SAME_ID_MESSAGE_KEY = "cannot_contain_with_same_id";

  private final PersonMapper personMapper;
  private final PersonDataValidator personDataValidator;
  private final MessageBuilder messageBuilder;

  public CSVFileParser(
      PersonMapper personMapper,
      PersonDataValidator personDataValidator,
      MessageBuilder messageBuilder) {
    this.personMapper = personMapper;
    this.personDataValidator = personDataValidator;
    this.messageBuilder = messageBuilder;
  }

  @Override
  public Map<Long, Person> parseFile(String filePath) throws IOException {
    if (!filePath.endsWith(CSV_FILE_EXTENSION)) {
      throw new IllegalArgumentException(
          messageBuilder.buildMessage(EXTENSION_SHOULD_BE_CSV_MESSAGE_KEY));
    }
    Map<Long, Person> employeesMap = new HashMap<>();
    boolean hasCEO = false;

    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
      List<String[]> values = lines.map(line -> line.split(COLUMN_DELIMITER)).toList();
      if (values.size() > MAX_LINES + FILE_HEADER_COLUMNS_NUMBER) {
        throw new IllegalArgumentException(
            messageBuilder.buildMessage(
                MORE_LINES_MESSAGE_KEY, MAX_LINES + FILE_HEADER_COLUMNS_NUMBER, values.size()));
      }
      if (values.isEmpty()) {
        return Collections.emptyMap();
      }

      for (int i = FILE_HEADER_COLUMNS_NUMBER; i < values.size(); i++) {
        String[] personalData = values.get(i);
        if (!personDataValidator.validatePersonData(personalData)) {
          throw new IllegalArgumentException(
              messageBuilder.buildMessage(INCORRECT_DATA_FOR_LINE_MESSAGE_KEY, i));
        }
        Person person = personMapper.createPerson(personalData);
        // we assume that file should contain only one CEO
        if (person instanceof CEO) {
          if (hasCEO) {
            throw new IllegalArgumentException(
                messageBuilder.buildMessage(MORE_THAN_ONE_CEO_MESSAGE_KEY));
          }
          hasCEO = true;
        }
        long id = person.getId();
        if (employeesMap.containsKey(id)) {
          throw new IllegalArgumentException(
              messageBuilder.buildMessage(CANNOT_CONTAIN_SAME_ID_MESSAGE_KEY, id));
        } else {
          employeesMap.put(id, person);
        }
      }
    }

    return employeesMap;
  }
}
