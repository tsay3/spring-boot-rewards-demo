package com.brooksource.saydemo.controller;

import com.brooksource.saydemo.model.Transaction;
import com.brooksource.saydemo.repository.TransactionRepository;
import com.brooksource.saydemo.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(RewardsController.class)
public class RewardsControllerTest {
//    @Autowired
//    private MockMvc mockMvc;

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    @Test
    public void rewardsTest() {
        Instant now = Instant.now();
        when(repository.findByMonth(0)).thenReturn(
                Arrays.asList(new Transaction(
                    Timestamp.from(now.minus(12, ChronoUnit.DAYS)),
                    2L,
                    new BigDecimal(55.01))
                )
        );
        when(repository.findByMonth(1)).thenReturn(
                Arrays.asList(new Transaction(
                                Timestamp.from(now.minus(40, ChronoUnit.DAYS)),
                                1L,
                                new BigDecimal(50.00)),
                        new Transaction(
                                Timestamp.from(now.minus(35, ChronoUnit.DAYS)),
                                2L,
                                new BigDecimal(20.36)),
                        new Transaction(
                                Timestamp.from(now.minus(32, ChronoUnit.DAYS)),
                                4L,
                                new BigDecimal(75.01)),
                        new Transaction(
                                Timestamp.from(now.minus(25, ChronoUnit.DAYS)),
                                1L,
                                new BigDecimal(100.00))
                )
        );
        when(repository.findByMonth(2)).thenReturn(
            Arrays.asList(new Transaction(
                    Timestamp.from(now.minus(60, ChronoUnit.DAYS)),
                    3L,
                    new BigDecimal(120.49)),
                new Transaction(
                    Timestamp.from(now.minus(60, ChronoUnit.DAYS)),
                    4L,
                    new BigDecimal(25.00))
            )
        );
        assertThat(service.getPointsForMonth(1L, 0)).isEqualTo(0);
        assertThat(service.getPointsForMonth(2L, 0)).isEqualTo(5);
        assertThat(service.getPointsForMonth(3L, 0)).isEqualTo(0);
        assertThat(service.getPointsForMonth(4L, 0)).isEqualTo(0);
        assertThat(service.getPointsForMonth(1L, 1)).isEqualTo(50);
        assertThat(service.getPointsForMonth(2L, 1)).isEqualTo(0);
        assertThat(service.getPointsForMonth(3L, 1)).isEqualTo(0);
        assertThat(service.getPointsForMonth(4L, 1)).isEqualTo(0);
        assertThat(service.getPointsForMonth(1L, 2)).isEqualTo(0);
        assertThat(service.getPointsForMonth(2L, 2)).isEqualTo(0);
        assertThat(service.getPointsForMonth(3L, 2)).isEqualTo(90);
        assertThat(service.getPointsForMonth(4L, 2)).isEqualTo(0);
//        when(repository.findById(1L)).thenReturn(Optional.of(
//                new Transaction(
//                        Timestamp.from(now.minus(40, ChronoUnit.DAYS)),
//                        1L,
//                        new BigDecimal(100.00))));

    }
}
