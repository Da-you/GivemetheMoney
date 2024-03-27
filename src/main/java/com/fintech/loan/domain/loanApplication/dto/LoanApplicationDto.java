package com.fintech.loan.domain.loanApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class LoanApplicationDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoanApplicationRequest {
        private String name;
        private String cellPhone;
        private String email;
        private BigDecimal hopeAmount;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoanApplicationResponse {
        private String name;
        private String cellPhone;
        private String email;
        private BigDecimal hopeAmount;
        private LocalDateTime appliedAt;
        private LocalDateTime contractAt;

    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AcceptTermsDto {
        private List<Long> acceptTermsIds;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FileInfoDto {
        private String name;
        private String url;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GrantAmount {
        private Long applicationId;
        private BigDecimal approvalAmount;
    }
}
