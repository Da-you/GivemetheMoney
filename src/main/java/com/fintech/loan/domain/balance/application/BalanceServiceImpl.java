package com.fintech.loan.domain.balance.application;

import com.fintech.loan.domain.balance.domain.Balance;
import com.fintech.loan.domain.balance.repository.BalanceRepository;
import com.fintech.loan.global.exception.BaseException;
import com.fintech.loan.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.fintech.loan.domain.balance.dto.BalanceDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {
    private final ModelMapper modelMapper;
    private final BalanceRepository balanceRepository;

    @Override
    public BalanceResponse create(Long applicationId, BalanceRequest request) {
        if (!balanceRepository.findByApplicationId(applicationId).isPresent()) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        Balance balance = modelMapper.map(request, Balance.class);
        balance.setApplicationId(applicationId);
        balance.setBalance(request.getEntryAmount());

        balanceRepository.findByApplicationId(applicationId).ifPresent(b -> {
            balance.setId(b.getId());
            balance.setIsDeleted(b.getIsDeleted());
            balance.setCreatedAt(b.getCreatedAt());
            balance.setUpdatedAt(b.getUpdatedAt());
        });


        return modelMapper.map(balanceRepository.save(balance), BalanceResponse.class);
    }

    @Override
    public BalanceResponse updateBalance(Long applicationId, BalanceUpdateRequest request) {
        // check balance
        Balance balance = balanceRepository.findByApplicationId(applicationId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));

        // update
        BigDecimal before = request.getBeforeEntryAmount();
        BigDecimal after = request.getAfterEntryAmount();
        BigDecimal update = balance.getBalance();
        // as - is > to - be
        update = update.subtract(before).add(after);
        balance.setBalance(update);


        return modelMapper.map(balanceRepository.save(balance), BalanceResponse.class);
    }
}
