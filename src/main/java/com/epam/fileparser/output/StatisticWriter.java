package com.epam.fileparser.output;

import com.epam.fileparser.statistic.StatisticHolder;

/**
 * StatisticWriter is a simple interface that represents a writer that is capable of writing
 * statistic. It requires defining a single method writeStatistic, which takes the statisticHolder
 * and not return anything.
 */
public interface StatisticWriter {
  void writeStatistic(StatisticHolder statisticHolder);
}
