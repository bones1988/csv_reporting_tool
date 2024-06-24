package com.epam.fileparser.personutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.epam.fileparser.repository.PersonRepositoryInMemory;
import com.epam.fileparser.statisticutils.StatisticHolder;
import com.epam.fileparser.statisticutils.StatisticHolderImpl;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonsStatisticCalculatorImplTest {
  @Mock private PersonRepositoryInMemory personRepository;
  @InjectMocks private PersonsStatisticCalculatorImpl personStatisticCalculator;

  @Test
  public void calculateStatisticShouldReturnEmptyStatisticForEmptyEmployees() {
    // when
    StatisticHolder emptyStatisticHolder = new StatisticHolderImpl();
    StatisticHolder statisticHolder = new StatisticHolderImpl();
    when(personRepository.getAllPersons()).thenReturn(Collections.EMPTY_MAP);
    StatisticHolder actual = personStatisticCalculator.calculateStatistic(statisticHolder);

    // then
    assertEquals(emptyStatisticHolder, actual);
  }
}
