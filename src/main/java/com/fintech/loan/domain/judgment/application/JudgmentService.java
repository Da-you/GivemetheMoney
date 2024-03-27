package com.fintech.loan.domain.judgment.application;

import com.fintech.loan.domain.judgment.dto.JudgmentDto.*;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto;

public interface JudgmentService {

    JudgmentResponse create(JudgmentRequest request);
    JudgmentResponse get(Long judgmentId);
    JudgmentResponse getJudgmentOfApplication(Long applicationId);

    JudgmentResponse updateJudgment(Long judgmentId, JudgmentRequest request);

    void delete(Long judgmentId);

    LoanApplicationDto.GrantAmount grant(Long judgmentId);
}
