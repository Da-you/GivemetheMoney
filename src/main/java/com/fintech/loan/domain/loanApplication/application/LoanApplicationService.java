package com.fintech.loan.domain.loanApplication.application;

import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.AcceptTermsDto;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationRequest;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationResponse;

public interface LoanApplicationService {

    LoanApplicationResponse create(LoanApplicationRequest request);

    LoanApplicationResponse get(Long applicationId);

    LoanApplicationResponse update(Long applicationId, LoanApplicationRequest request);

    void delete(Long applicationId);

    Boolean acceptTerms(Long applicationId, AcceptTermsDto request);

    LoanApplicationResponse contract(Long applicationId);


}
