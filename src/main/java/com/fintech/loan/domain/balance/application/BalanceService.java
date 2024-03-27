package com.fintech.loan.domain.balance.application;

import com.fintech.loan.domain.balance.dto.BalanceDto;
import com.fintech.loan.domain.balance.dto.BalanceDto.BalanceRequest;
import com.fintech.loan.domain.balance.dto.BalanceDto.BalanceResponse;
import com.fintech.loan.domain.balance.dto.BalanceDto.BalanceUpdateRequest;

public interface BalanceService {

    BalanceResponse create(Long applicationId, BalanceRequest request);

    BalanceResponse updateBalance(Long applicationId, BalanceUpdateRequest request);
}
