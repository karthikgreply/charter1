package com.assignment.charter1.model;

import java.util.Map;

public class RewardPointsResponse {
    private Long customerId;
    private Map<String, Integer> pointsPerMonth;
    private Integer totalPoints;

    public RewardPointsResponse(Long customerId, Map<String, Integer> pointsPerMonth, Integer totalPoints) {
        this.customerId = customerId;
        this.pointsPerMonth = pointsPerMonth;
        this.totalPoints = totalPoints;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getPointsPerMonth() {
        return pointsPerMonth;
    }

    public void setPointsPerMonth(Map<String, Integer> pointsPerMonth) {
        this.pointsPerMonth = pointsPerMonth;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }
}
