package com.epam.fileparser.personutils;

import com.epam.fileparser.statisticutils.StatisticHolder;

/**
 * PersonsStatisticCalculator is an interface that defines a contract for a calculator capable of
 * calculating statistics from a given StatisticHolder object.
 */
public interface PersonsStatisticCalculator {
  /**
   * This method calculates and updates the given StatisticHolder object with the calculated
   * statistics.
   *
   * @param statisticHolder - A StatisticHolder object containing the data for which the statistics
   *     will be calculated
   * @return An updated StatisticHolder object containing the calculated statistics
   */
  StatisticHolder calculateStatistic(StatisticHolder statisticHolder);
}
