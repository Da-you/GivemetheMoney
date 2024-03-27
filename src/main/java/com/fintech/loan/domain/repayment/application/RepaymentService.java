package com.fintech.loan.domain.repayment.application;

import com.fintech.loan.domain.repayment.dto.RepaymentDto;

import java.util.List;

import static com.fintech.loan.domain.repayment.dto.RepaymentDto.*;

public interface RepaymentService {
    RepaymentResponse create(Long applicationId, RepaymentRequest request);

    List<RepaymentListResponse> get(Long applicationId);
}
