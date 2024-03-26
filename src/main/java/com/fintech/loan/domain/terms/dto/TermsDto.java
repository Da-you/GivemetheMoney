package com.fintech.loan.domain.terms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class TermsDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsRequest {
        private String name;
        private String termsDetailUrl;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TermsResponse {

        private Long termsId;
        private String name;
        private String termsDetailUrl;
    }
}
