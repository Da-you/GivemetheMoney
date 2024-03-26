package com.fintech.loan.domain.judgment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class JudgmentDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JudgmentRequest {

        private Long applicationId;
        private String name;
        private BigDecimal approvalAmount;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JudgmentResponse {
        private Long judgmentId;
        private Long applicationId;
        private String name;
        private BigDecimal approvalAmount;

    }
}
