package com.fintech.loan.domain.loanApplication.application;

import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationRequest;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationResponse;

public interface LoanApplicationService {

    LoanApplicationResponse create(LoanApplicationRequest request);

    LoanApplicationResponse get(Long applicationId);

    LoanApplicationResponse update(Long applicationId, LoanApplicationRequest request);

    void delete(Long applicationId);
}
