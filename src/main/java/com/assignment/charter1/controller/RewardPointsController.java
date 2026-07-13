package com.assignment.charter1.controller;

import com.assignment.charter1.model.RewardPointsResponse;
import com.assignment.charter1.service.RewardPointsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardPointsController {

    private final RewardPointsService rewardPointsService;

    public RewardPointsController(RewardPointsService rewardPointsService) {
        this.rewardPointsService = rewardPointsService;
    }

    @GetMapping("/calculate")
    public List<RewardPointsResponse> getRewardPoints() {
        return rewardPointsService.calculateAllCustomersRewardPoints();
    }
}
