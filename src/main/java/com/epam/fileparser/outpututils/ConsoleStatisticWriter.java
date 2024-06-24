package com.epam.fileparser.outpututils;

import com.epam.fileparser.statisticutils.StatisticHolder;
import com.epam.fileparser.statisticutils.model.ManagerStatisticHolder;
import com.epam.fileparser.statisticutils.model.SalaryStatisticHolder;
import java.util.List;

public class ConsoleStatisticWriter implements StatisticWriter {
  @Override
  public void writeStatistic(StatisticHolder statisticHolder) {
    List<SalaryStatisticHolder> lowSlariesList = statisticHolder.getLowSalaryList();
    List<SalaryStatisticHolder> highSlariesList = statisticHolder.getHighSalaryList();
    List<ManagerStatisticHolder> wrongManagersChain = statisticHolder.getWrongManagersChainList();
    writeLowSalaryStatistic(lowSlariesList);
    writeHighSalaryStatistic(highSlariesList);
    writeWrongManagersStatistic(wrongManagersChain);
  }

  private void writeLowSalaryStatistic(List<SalaryStatisticHolder> lowSalaryStatisticList) {
    System.out.println("Managers earn less than they should:");

    lowSalaryStatisticList.forEach(
        s -> System.out.println(s.getPerson() + " By salary:" + s.getBySalary()));
    System.out.println();
  }

  private void writeHighSalaryStatistic(List<SalaryStatisticHolder> highSalaryStatisticList) {
    System.out.println("Managers earn more than they should:");

    highSalaryStatisticList.forEach(
        s -> System.out.println(s.getPerson() + " By salary:" + s.getBySalary()));
    System.out.println();
  }

  private void writeWrongManagersStatistic(List<ManagerStatisticHolder> wrongManagersList) {
    System.out.println(" employees have a reporting line which is too long:");

    wrongManagersList.forEach(s -> System.out.println(s.getPerson() + " By:" + s.getByNumber()));
    System.out.println();
  }
}
