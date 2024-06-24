package com.epam.fileparser.outpututils;

import com.epam.fileparser.statisticutils.StatisticHolder;

public interface StatisticWriter {
  void writeStatistic(StatisticHolder statisticHolder);
}
