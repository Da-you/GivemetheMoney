package com.fintech.loan.domain.repayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RepaymentListResponse{
        private Long repaymentId;
        private BigDecimal repaymentAmount;
    }
}
