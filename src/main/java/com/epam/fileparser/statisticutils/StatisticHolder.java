package com.epam.fileparser.statisticutils;

import com.epam.fileparser.statisticutils.model.ManagerStatisticHolder;
import com.epam.fileparser.statisticutils.model.SalaryStatisticHolder;
import java.util.List;

/**
 * StatisticHolder is an interface that provides methods for storing and retrieving statistical data
 * of different types, such as manager-related statistics or salary-related statistics.
 */
public interface StatisticHolder {
  /**
   * Record a statistic record with too long managers chain.
   *
   * @param managerStatisticHolder - A ManagerStatisticHolder object containing manager-related
   *     statistical data.
   */
  void addWrongManagerChainPerson(ManagerStatisticHolder managerStatisticHolder);

  /**
   * Retrieve a list of statistics with wrong long managers chain.
   *
   * @return A list of ManagerStatisticHolder objects containing manager-related statistical data.
   */
  List<ManagerStatisticHolder> getWrongManagersChainList();

  /**
   * Record a low salary statistical entry.
   *
   * @param salaryStatisticHolder - A SalaryStatisticHolder object containing low salary-related
   *     statistical data.
   */
  void addLowSalaryStatistic(SalaryStatisticHolder salaryStatisticHolder);

  /**
   * Retrieve a list of low salary statistical entries.
   *
   * @return A list of SalaryStatisticHolder objects containing low salary-related statistical data.
   */
  List<SalaryStatisticHolder> getLowSalaryList();

  /**
   * Record a high salary statistical entry.
   *
   * @param salaryStatisticHolder - A SalaryStatisticHolder object containing high salary-related
   *     statistical data.
   */
  void addHighSalary(SalaryStatisticHolder salaryStatisticHolder);

  /**
   * Retrieve a list of high salary statistical entries.
   *
   * @return A list of SalaryStatisticHolder objects containing high salary-related statistical
   *     data.
   */
  List<SalaryStatisticHolder> getHighSalaryList();
}
