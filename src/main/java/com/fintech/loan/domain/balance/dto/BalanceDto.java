package com.fintech.loan.domain.balance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class BalanceDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BalanceRequest{
        private Long applicationId;
        private BigDecimal entryAmount;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BalanceUpdateRequest{
        private Long applicationId;
        private BigDecimal beforeEntryAmount;
        private BigDecimal afterEntryAmount;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BalanceResponse{
        private Long balanceId;
        private Long applicationId;
        private BigDecimal balance;
    }
}
