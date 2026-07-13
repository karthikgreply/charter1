package com.assignment.charter1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RewardPointsResponse {
    private Long customerId;
    private Map<String, Integer> pointsPerMonth;
    private Integer totalPoints;
}
