package com.assignment.charter1.controller;

import com.assignment.charter1.model.RewardPointsResponse;
import com.assignment.charter1.model.Transaction;
import com.assignment.charter1.repository.TransactionRepository;
import com.assignment.charter1.service.RewardPointsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardPointsController {

    private final RewardPointsService rewardPointsService;
    private final TransactionRepository transactionRepository;

    public RewardPointsController(RewardPointsService rewardPointsService, TransactionRepository transactionRepository) {
        this.rewardPointsService = rewardPointsService;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/calculate")
    public List<RewardPointsResponse> getRewardPoints() {
        List<Transaction> transactions = transactionRepository.findAll();
        return rewardPointsService.calculateRewardPoints(transactions);
    }
}
