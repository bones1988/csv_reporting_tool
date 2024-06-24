package com.epam.fileparser.statisticutils;

import com.epam.fileparser.statisticutils.model.ManagerStatisticHolder;
import com.epam.fileparser.statisticutils.model.SalaryStatisticHolder;
import java.util.List;

public interface StatisticHolder {
  void addWrongManager(ManagerStatisticHolder managerStatisticHolder);

  List<ManagerStatisticHolder> getWrongManagersChainList();

  void addLowSalaryStatistic(SalaryStatisticHolder salaryStatisticHolder);

  List<SalaryStatisticHolder> getLowSalaryList();

  void addHighSalary(SalaryStatisticHolder salaryStatisticHolder);

  List<SalaryStatisticHolder> getHighSalaryList();
}
