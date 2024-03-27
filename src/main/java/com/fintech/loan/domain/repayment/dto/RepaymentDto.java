package com.fintech.loan.domain.repayment.dto;

import lombok.*;

import java.math.BigDecimal;

public class RepaymentDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RepaymentRequest{
        private BigDecimal repaymentAmount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RepaymentResponse{
        private Long applicationId;
        private BigDecimal repaymentAmount;
        private BigDecimal Balance;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateRepaymentResponse{
        private Long applicationId;
        private BigDecimal beforeRepaymentAmount;
        private BigDecimal afterRepaymentAmount;
        private BigDecimal balance;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RepaymentListResponse{
        private Long repaymentId;
        private BigDecimal repaymentAmount;
    }
}
