package com.fintech.loan.domain.consulting.application;

import com.fintech.loan.domain.consulting.dto.ConsultingDto.ConsultingRequest;
import com.fintech.loan.domain.consulting.dto.ConsultingDto.ConsultingResponse;

public interface ConsultingService {
    ConsultingResponse create(ConsultingRequest request);
    ConsultingResponse get(Long id);
    ConsultingResponse update(Long id,ConsultingRequest request);
    void delete(Long id);
}
