package com.assignment.charter1.service;

import com.assignment.charter1.model.RewardPointsResponse;
import com.assignment.charter1.model.Transaction;
import com.assignment.charter1.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardPointsService {

    private final TransactionRepository transactionRepository;

    public RewardPointsService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<RewardPointsResponse> calculateAllCustomersRewardPoints() {
        List<Transaction> transactions = transactionRepository.findAll();
        return calculateRewardPoints(transactions);
    }

    private int calculatePoints(BigDecimal amount) {
        if (amount == null) return 0;

        int amountInt = amount.intValue();
        int points = 0;

        if (amountInt > 100) {
            points += 2 * (amountInt - 100) + 50;
        } else if (amountInt > 50) {
            points += (amountInt - 50);
        }
        return points;
    }

    private List<RewardPointsResponse> calculateRewardPoints(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCustomerId))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Integer> pointsPerMonth = entry.getValue().stream()
                            .collect(Collectors.groupingBy(
                                    t -> t.getTransactionDate().getMonth().name(),
                                    Collectors.summingInt(t -> calculatePoints(t.getTransactionAmount()))
                            ));

                    int totalPoints = pointsPerMonth.values().stream().mapToInt(Integer::intValue).sum();
                    return new RewardPointsResponse(entry.getKey(), pointsPerMonth, totalPoints);
                })
                .collect(Collectors.toList());
    }
}
