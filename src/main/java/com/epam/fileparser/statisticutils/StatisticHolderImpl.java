package com.epam.fileparser.statisticutils;

import com.epam.fileparser.statisticutils.model.ManagerStatisticHolder;
import com.epam.fileparser.statisticutils.model.SalaryStatisticHolder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatisticHolderImpl implements StatisticHolder {
  private final List<ManagerStatisticHolder> wrongManagersChainList = new ArrayList<>();
  private final List<SalaryStatisticHolder> lowSalaryList = new ArrayList<>();
  private final List<SalaryStatisticHolder> highSalaryList = new ArrayList<>();

  public void addWrongManager(ManagerStatisticHolder managerStatisticHolder) {
    wrongManagersChainList.add(managerStatisticHolder);
  }

  public List<ManagerStatisticHolder> getWrongManagersChainList() {
    return this.wrongManagersChainList;
  }

  public void addLowSalaryStatistic(SalaryStatisticHolder salaryStatisticHolder) {
    this.lowSalaryList.add(salaryStatisticHolder);
  }

  public List<SalaryStatisticHolder> getLowSalaryList() {
    return this.lowSalaryList;
  }

  public void addHighSalary(SalaryStatisticHolder salaryStatisticHolder) {
    this.highSalaryList.add(salaryStatisticHolder);
  }

  public List<SalaryStatisticHolder> getHighSalaryList() {
    return this.highSalaryList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StatisticHolderImpl that = (StatisticHolderImpl) o;
    return Objects.equals(wrongManagersChainList, that.wrongManagersChainList)
        && Objects.equals(lowSalaryList, that.lowSalaryList)
        && Objects.equals(highSalaryList, that.highSalaryList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(wrongManagersChainList, lowSalaryList, highSalaryList);
  }
}
