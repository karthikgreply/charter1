package com.assignment.charter1.service;

import com.assignment.charter1.model.RewardPointsResponse;
import com.assignment.charter1.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardPointsService {

    public int calculatePoints(Double amount) {
        int points = 0;
        if (amount > 100) {
            points += 2 * (Math.floor(amount) - 100);
            points += 50;
        } else if (amount > 50) {
            points += 1 * (Math.floor(amount) - 50);
        }
        return points;
    }

    public List<RewardPointsResponse> calculateRewardPoints(List<Transaction> transactions) {
        Map<Long, List<Transaction>> transactionsByCustomer = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId));

        List<RewardPointsResponse> responses = new ArrayList<>();

        for (Map.Entry<Long, List<Transaction>> entry : transactionsByCustomer.entrySet()) {
            Long customerId = entry.getKey();
            List<Transaction> customerTransactions = entry.getValue();

            Map<String, Integer> pointsPerMonth = new HashMap<>();
            int totalPoints = 0;

            for (Transaction transaction : customerTransactions) {
                Month month = transaction.getTransactionDate().getMonth();
                String monthName = month.name();
                int points = calculatePoints(transaction.getTransactionAmount());

                pointsPerMonth.put(monthName, pointsPerMonth.getOrDefault(monthName, 0) + points);
                totalPoints += points;
            }

            responses.add(new RewardPointsResponse(customerId, pointsPerMonth, totalPoints));
        }

        return responses;
    }
}
