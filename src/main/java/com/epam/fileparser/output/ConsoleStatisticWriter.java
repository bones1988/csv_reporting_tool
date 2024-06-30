package com.epam.fileparser.output;

import com.epam.fileparser.message.MessageBuilder;
import com.epam.fileparser.statistic.StatisticHolder;
import com.epam.fileparser.statistic.model.ManagerStatisticHolder;
import com.epam.fileparser.statistic.model.SalaryStatisticHolder;
import java.util.List;

/**
 * ConsoleStatisticWriter is a class that implements the output functionality defined in the
 * StatisticWriter interface. It writes the statistic to console.
 */
public class ConsoleStatisticWriter implements StatisticWriter {
  private static final String NO_MANAGERS_SALARY_MESSAGE_KEY = "no_managers_salary";
  private static final String LESS_MESSAGE_KEY = "less";
  private static final String MORE_MESSAGE_KEY = "more";
  private static final String MANAGERS_SALARY_HEADER_MESSAGE_KEY = "managers_salary_header";
  private static final String PERSON_BY_SALARY_MESSAGE_KEY = "person_by_salary";
  private static final String NO_EMPLOYEES_LONG_REPORTING_LINE_MESSAGE_KEY =
      "no_employees_long_reporting_line";
  private static final String TOO_LONG_REPORTING_LINE_HEADER_MESSAGE_KEY =
      "too_long_reporting_line_header";
  private static final String PERSON_BY_REPORTING_LINE_LENGTH_MESSAGE_KEY =
      "person_by_reporting_line_length";

  private final MessageBuilder messageBuilder;

  public ConsoleStatisticWriter(MessageBuilder messageBuilder) {
    this.messageBuilder = messageBuilder;
  }

  @Override
  public void writeStatistic(StatisticHolder statisticHolder) {

    List<SalaryStatisticHolder> lowSlariesList = statisticHolder.getLowSalaryList();
    String lessKey = messageBuilder.buildMessage(LESS_MESSAGE_KEY);
    writeSalaryStatistic(lowSlariesList, lessKey);

    List<SalaryStatisticHolder> highSlariesList = statisticHolder.getHighSalaryList();
    String moreKey = messageBuilder.buildMessage(MORE_MESSAGE_KEY);
    writeSalaryStatistic(highSlariesList, moreKey);

    List<ManagerStatisticHolder> wrongManagersChain = statisticHolder.getWrongManagersChainList();
    writeWrongManagersStatistic(wrongManagersChain);
  }

  private void writeSalaryStatistic(List<SalaryStatisticHolder> salaryStatisticList, String key) {
    if (salaryStatisticList.isEmpty()) {
      String noManagersString = messageBuilder.buildMessage(NO_MANAGERS_SALARY_MESSAGE_KEY, key);
      System.out.println(noManagersString);
      return;
    }
    String header = messageBuilder.buildMessage(MANAGERS_SALARY_HEADER_MESSAGE_KEY, key);
    System.out.println(header);

    salaryStatisticList.forEach(
        s ->
            System.out.println(
                messageBuilder.buildMessage(
                    PERSON_BY_SALARY_MESSAGE_KEY,
                    s.getPerson().getStringOutput(messageBuilder),
                    s.getBySalary())));
    System.out.println();
  }

  private void writeWrongManagersStatistic(List<ManagerStatisticHolder> wrongManagersList) {
    if (wrongManagersList.isEmpty()) {
      System.out.println(messageBuilder.buildMessage(NO_EMPLOYEES_LONG_REPORTING_LINE_MESSAGE_KEY));
      return;
    }
    System.out.println(messageBuilder.buildMessage(TOO_LONG_REPORTING_LINE_HEADER_MESSAGE_KEY));

    wrongManagersList.forEach(
        s ->
            System.out.println(
                messageBuilder.buildMessage(
                    PERSON_BY_REPORTING_LINE_LENGTH_MESSAGE_KEY,
                    s.getPerson().getStringOutput(messageBuilder),
                    s.getByNumber())));
  }
}
