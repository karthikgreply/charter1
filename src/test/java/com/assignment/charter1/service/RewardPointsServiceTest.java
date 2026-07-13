package com.assignment.charter1.service;

import com.assignment.charter1.model.RewardPointsResponse;
import com.assignment.charter1.model.Transaction;
import com.assignment.charter1.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RewardPointsServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardPointsService rewardPointsService;

    private List<Transaction> mockTransactions;

    @BeforeEach
    void setUp() {
        // Create mock data that will be returned by the repository
        Transaction t1 = new Transaction(1L, 1L, LocalDate.of(2026, Month.JANUARY, 15), new BigDecimal("120.0")); // 90 points
        Transaction t2 = new Transaction(2L, 1L, LocalDate.of(2026, Month.JANUARY, 20), new BigDecimal("40.0"));  // 0 points
        Transaction t3 = new Transaction(3L, 1L, LocalDate.of(2026, Month.FEBRUARY, 10), new BigDecimal("150"));// 150 points
        Transaction t4 = new Transaction(4L, 2L, LocalDate.of(2026, Month.JANUARY, 12), new BigDecimal("200.0"));// 250 points
        Transaction t5 = new Transaction(5L, 2L, LocalDate.of(2026, Month.MARCH, 15), new BigDecimal("99.0")); // 49 points

        mockTransactions = Arrays.asList(t1, t2, t3, t4, t5);
    }

    @Test
    void testCalculateAllCustomersRewardPoints() {
        // Configure the mock repository to return our mock data when findAll() is called
        when(transactionRepository.findAll()).thenReturn(mockTransactions);

        // Call the method we want to test
        List<RewardPointsResponse> responses = rewardPointsService.calculateAllCustomersRewardPoints();

        // Assert the results
        assertEquals(2, responses.size()); // We have 2 unique customers

        // Check results for Customer 1
        RewardPointsResponse customer1Response = responses.stream().filter(r -> r.getCustomerId() == 1L).findFirst().orElse(null);
        assert customer1Response != null;
        assertEquals(1L, customer1Response.getCustomerId());

        Map<String, Integer> customer1Points = customer1Response.getPointsPerMonth();
        assertEquals(90, customer1Points.get("JANUARY"));
        assertEquals(150, customer1Points.get("FEBRUARY"));
        assertEquals(240, customer1Response.getTotalPoints()); // 90 + 150

        // Check results for Customer 2
        RewardPointsResponse customer2Response = responses.stream().filter(r -> r.getCustomerId() == 2L).findFirst().orElse(null);
        assert customer2Response != null;
        assertEquals(2L, customer2Response.getCustomerId());

        Map<String, Integer> customer2Points = customer2Response.getPointsPerMonth();
        assertEquals(250, customer2Points.get("JANUARY"));
        assertEquals(49, customer2Points.get("MARCH"));
        assertEquals(299, customer2Response.getTotalPoints()); // 250 + 49
    }
}
