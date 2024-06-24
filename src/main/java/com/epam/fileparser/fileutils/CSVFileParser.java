package com.epam.fileparser.fileutils;

import com.epam.fileparser.model.CEO;
import com.epam.fileparser.model.Person;
import com.epam.fileparser.personutils.PersonDataValidator;
import com.epam.fileparser.personutils.PersonMapper;
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
  private final PersonMapper personMapper;
  private final PersonDataValidator personDataValidator;

  public CSVFileParser(PersonMapper personMapper, PersonDataValidator personDataValidator) {
    this.personMapper = personMapper;
    this.personDataValidator = personDataValidator;
  }

  @Override
  public Map<Long, Person> parseFile(String filePath) throws IOException {
    if (!filePath.endsWith(CSV_FILE_EXTENSION)) {
      throw new IllegalArgumentException("File extension should be .csv to proceed");
    }
    Map<Long, Person> employeesMap = new HashMap<>();
    boolean hasCEO = false;

    try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
      List<String[]> values = lines.map(line -> line.split(COLUMN_DELIMITER)).toList();
      if (values.size() > MAX_LINES + FILE_HEADER_COLUMNS_NUMBER) {
        throw new IllegalArgumentException(
            "Provided file has more than "
                + MAX_LINES
                + FILE_HEADER_COLUMNS_NUMBER
                + " lines :"
                + values.size());
      }
      if (values.isEmpty()) {
        return Collections.emptyMap();
      }

      for (int i = FILE_HEADER_COLUMNS_NUMBER; i < values.size(); i++) {
        String[] personalData = values.get(i);
        if (!personDataValidator.validatePersonData(personalData)) {
          throw new IllegalArgumentException("Incorrect data for line:" + i);
        }
        Person person = personMapper.createPerson(personalData);
        // we assume that file should contain only one CEO
        if (person instanceof CEO) {
          if (hasCEO) {
            throw new IllegalArgumentException("File cannot contain more than 1 CEO");
          }
          hasCEO = true;
        }
        long id = person.getId();
        if (employeesMap.containsKey(id)) {
          throw new IllegalArgumentException(
              "File cannot contain more than 1 employee with same id. Id:" + id);
        } else {
          employeesMap.put(id, person);
        }
      }
    }

    return employeesMap;
  }
}
