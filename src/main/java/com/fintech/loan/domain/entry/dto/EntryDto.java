package com.fintech.loan.domain.entry.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

public class EntryDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EntryRequest {

        private BigDecimal entryAmount;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EntryResponse {

        private Long entryId;
        private Long applicationId;
        private BigDecimal entryAmount;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateEntryResponse {

        private Long entryId;
        private Long applicationId;
        private BigDecimal beforeEntryAmount;
        private BigDecimal afterEntryAmount;

    }
}
