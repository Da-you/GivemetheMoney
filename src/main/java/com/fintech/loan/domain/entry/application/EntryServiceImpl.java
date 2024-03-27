package com.fintech.loan.domain.entry.application;

import com.fintech.loan.domain.balance.application.BalanceService;
import com.fintech.loan.domain.balance.dto.BalanceDto;
import com.fintech.loan.domain.balance.dto.BalanceDto.BalanceUpdateRequest;
import com.fintech.loan.domain.entry.domain.Entry;
import com.fintech.loan.domain.entry.dto.EntryDto.EntryResponse;
import com.fintech.loan.domain.entry.repository.EntryRepository;
import com.fintech.loan.domain.loanApplication.domain.LoanApplication;
import com.fintech.loan.domain.loanApplication.repository.LoanApplicationRepository;
import com.fintech.loan.global.exception.BaseException;
import com.fintech.loan.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

import static com.fintech.loan.domain.entry.dto.EntryDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {
    private final ModelMapper modelMapper;
    private final EntryRepository entryRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final BalanceService balanceService;

    @Override
    public EntryResponse create(Long applicationId, EntryRequest request) {
        // 계약 체결 여부 검증
        if (!isPresentContractedAtApplication(applicationId)) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        Entry entry = modelMapper.map(request, Entry.class);
        // 대출 잔고 관리
        balanceService.create(applicationId, BalanceDto.BalanceRequest.builder()
                .entryAmount(request.getEntryAmount())
                .build());

        return modelMapper.map(entryRepository.save(entry), EntryResponse.class);
    }

    @Override
    public EntryResponse get(Long applicationId) {
        Optional<Entry> entry = entryRepository.findByApplicationId(applicationId);

        if (entry.isPresent()) {
            modelMapper.map(entry, EntryResponse.class);
        }
        return null;
    }

    private Boolean isPresentContractedAtApplication(Long application) {
        Optional<LoanApplication> existed = loanApplicationRepository.findById(application);
        if (existed.isEmpty()) {
            return false;
        }
        return existed.get().getContractedAt() != null;
    }

    @Override
    public UpdateEntryResponse update(Long entryId, EntryRequest request) {
        // entry 검증
        Entry entry = entryRepository.findById(entryId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));
        // before -> after
        BigDecimal before = entry.getEntryAmount();
        entry.setEntryAmount(request.getEntryAmount());
        entryRepository.save(entry);
        // balance update
        balanceService.updateBalance(entry.getApplicationId(),
                BalanceUpdateRequest.builder()
                        .beforeEntryAmount(before)
                        .afterEntryAmount(request.getEntryAmount())
                        .build());
        return UpdateEntryResponse.builder()
                .entryId(entryId)
                .applicationId(entry.getApplicationId())
                .beforeEntryAmount(before)
                .afterEntryAmount(request.getEntryAmount())
                .build();
    }

    @Override
    public void delete(Long entryId) {
        Entry entry = entryRepository.findById(entryId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));

        entry.setIsDeleted(true);
        entryRepository.save(entry);

        // balance 0으로 변경
        balanceService.updateBalance(entry.getApplicationId(), BalanceUpdateRequest.builder()
                .beforeEntryAmount(entry.getEntryAmount())
                .afterEntryAmount(BigDecimal.ZERO)
                .build());
    }


}
