package com.epam.fileparser.fileutils;

import com.epam.fileparser.model.Person;
import java.io.IOException;
import java.util.Map;

public interface FileParser {
  Map<Long, Person> parseFile(String filePath) throws IOException;
}
