package com.fintech.loan.domain.consulting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ConsultingDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConsultingRequest {
        private String name;
        private String cellPhone;
        private String email;
        private String memo;

        private String address;
        private String city;
        private String zipCode;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConsultingResponse {

        private Long id;
        private String name;
        private String cellPhone;
        private String email;
        private String memo;

        private String address;
        private String city;
        private String zipCode;
        private LocalDateTime appliedAt;
    }
}
