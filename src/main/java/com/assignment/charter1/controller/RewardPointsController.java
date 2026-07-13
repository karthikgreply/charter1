package com.assignment.charter1.controller;

import com.assignment.charter1.model.RewardPointsResponse;
import com.assignment.charter1.model.Transaction;
import com.assignment.charter1.service.RewardPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardPointsController {

    private final RewardPointsService rewardPointsService;

    @Autowired
    public RewardPointsController(RewardPointsService rewardPointsService) {
        this.rewardPointsService = rewardPointsService;
    }

    @GetMapping("/calculate")
    public List<RewardPointsResponse> getRewardPoints() {
        // Create a mocked dataset
        List<Transaction> dataset = Arrays.asList(
                // Customer 1
                new Transaction(1L, LocalDate.of(2023, Month.JANUARY, 15), 120.0), // 90 points
                new Transaction(1L, LocalDate.of(2023, Month.JANUARY, 20), 40.0), // 0 points
                new Transaction(1L, LocalDate.of(2023, Month.FEBRUARY, 10), 150.5), // 150 points
                new Transaction(1L, LocalDate.of(2023, Month.MARCH, 5), 80.0),  // 30 points
                
                // Customer 2
                new Transaction(2L, LocalDate.of(2023, Month.JANUARY, 12), 200.0), // 250 points
                new Transaction(2L, LocalDate.of(2023, Month.FEBRUARY, 28), 60.0), // 10 points
                new Transaction(2L, LocalDate.of(2023, Month.MARCH, 15), 110.0), // 70 points
                
                // Customer 3
                new Transaction(3L, LocalDate.of(2023, Month.JANUARY, 1), 30.0), // 0 points
                new Transaction(3L, LocalDate.of(2023, Month.FEBRUARY, 1), 101.0), // 52 points
                new Transaction(3L, LocalDate.of(2023, Month.MARCH, 1), 99.0) // 49 points
        );

        return rewardPointsService.calculateRewardPoints(dataset);
    }
}
