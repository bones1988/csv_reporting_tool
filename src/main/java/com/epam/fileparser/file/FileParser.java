package com.epam.fileparser.file;

import com.epam.fileparser.model.Person;
import java.io.IOException;
import java.util.Map;

/**
 * FileParser is a simple interface that represents a parser that is capable of parsing files. It
 * requires defining a single method parseFile, which takes the path of a file and returns a map of
 * data.
 */
public interface FileParser {
  /**
   * Should get the file from the provided path and return map with person id as a key and person as
   * a value
   *
   * @param filePath String path to file
   * @return Map with person id as a key and person as a value
   * @throws IOException when some error reading file occurred
   */
  Map<Long, Person> parseFile(String filePath) throws IOException;
}
