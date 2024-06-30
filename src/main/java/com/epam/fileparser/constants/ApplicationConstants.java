package com.epam.fileparser.constants;

import java.math.RoundingMode;

/**
 * Simple class to store some constants shared over the application
 */
public class ApplicationConstants {
  public static final int DECIMAL_SCALE = 2;
  public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

  public static final int ZERO_COMPARE_TO = 0;

  public static final int EMPLOYEE_ID_FIELD_INDEX = 0;
  public static final int EMPLOYEE_FIRST_NAME_FIELD_INDEX = 1;
  public static final int EMPLOYEE_LAST_NAME_FIELD_INDEX = 2;
  public static final int EMPLOYEE_SALARY_FIELD_INDEX = 3;
  public static final int EMPLOYEE_MANAGER_ID_FIELD_INDEX = 4;
  public static final int EMPLOYEE_FIELDS_COUNT = 5;

  private ApplicationConstants() {}
}
