# File Parser

Application to parse .csv file and report to console statistic:

- which managers earn less than 120% of his direct subordinates average salary, and by how much
- which managers earn more than 150% of his direct subordinates average salary, and by how much
- which employees have a reporting line longer than 4, and by how much

## Prerequisites

Before you begin, ensure you have met the following requirements:

* JDK 19 or later
* Maven 3.2+
* Optional: An IDE that supports Java and Maven

## Setup and Installation

- Clone source code from repo https://github.com/bones1988/csv_reporting_tool
- Run mvn install in the folder where you cloned source code. If you have any problems with tests you can use command:
  mvn install -DskipTests

Follow these steps to run the application:

1. Ensure you have a properly formatted CSV file ready for processing.

2. Use the command below to run the application, replacing `[path_to_csv_file]` with the path to your CSV file:

   ```bash
   java -jar file-parser-1.0.jar [path_to_csv_file]

Please note: You must provide a valid path to a properly formatted CSV file for the application to run correctly.

## CSV file

to be processed by the application .csv file should have:

- 1 header line
- "," as a delimiter

Rows should be properly formatted:

- Id,firstName,lastName,salary,managerId
- Id should be unique for every user and positive number
- First and last name should be longer than 2 symbols
- only one CEO can be in file (employee without managerId)
- There shouldn't be any cyclic hierarchy (every employee should have straight reporting line to the CEO)
- File should not contain more than 1000 employee rows + 1 header row

## Features

* Precision to 2 decimals (cents). Values rounded when > 0.005 to up. Otherwise to down.
* Show statistic which managers earn less than 20% more than the average salary of its direct subordinates (if employee
  has no dependents his max and min salary will be assumed as 0)
* Show statistic which managers earn more than 50% more than the average salary of its direct subordinates (if employee
  has no dependents his max and min salary will be assumed as 0)
* Show statistic which employees have more than 4 managers between them and the CEO

## Running the tests

To run tests just run the following command:

* `mvn test`

## License

This project is licensed under the MIT License.

## Contact Information

Project Link: https://github.com/bones1988/csv_reporting_tool
