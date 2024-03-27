package com.fintech.loan.domain.repayment.application;

import com.fintech.loan.domain.balance.application.BalanceService;
import com.fintech.loan.domain.balance.dto.BalanceDto;
import com.fintech.loan.domain.balance.dto.BalanceDto.BalanceResponse;
import com.fintech.loan.domain.entry.domain.Entry;
import com.fintech.loan.domain.entry.repository.EntryRepository;
import com.fintech.loan.domain.loanApplication.domain.LoanApplication;
import com.fintech.loan.domain.loanApplication.repository.LoanApplicationRepository;
import com.fintech.loan.domain.model.RepaymentType;
import com.fintech.loan.domain.repayment.domain.Repayment;
import com.fintech.loan.domain.repayment.dto.RepaymentDto.RepaymentRequest;
import com.fintech.loan.domain.repayment.dto.RepaymentDto.RepaymentResponse;
import com.fintech.loan.domain.repayment.repoistory.RepaymentRepository;
import com.fintech.loan.global.exception.BaseException;
import com.fintech.loan.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fintech.loan.domain.repayment.dto.RepaymentDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepaymentServiceImpl implements RepaymentService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final RepaymentRepository repaymentRepository;
    private final EntryRepository entryRepository;
    private final BalanceService balanceService;
    private final ModelMapper modelMapper;

    @Override
    public RepaymentResponse create(Long applicationId, RepaymentRequest request) {

        // check list
        // 1. 계약을 완료한 신청 정보
        // 2. 집행이 되어있어야 함
        if (!isRepayableApplication(applicationId)) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        Repayment repayment = modelMapper.map(request, Repayment.class);
        repayment.setApplicationId(applicationId);
        repaymentRepository.save(repayment);


        // Balance
        // 500 > 100 원 상환 = 잔여 400
        BalanceResponse response = balanceService.repaymentBalance(applicationId, BalanceDto.BalanceRepaymentRequest.builder()
                .repaymentAmount(request.getRepaymentAmount())
                .repaymentType(RepaymentType.REMOVE)
                .build());

        RepaymentResponse result = modelMapper.map(repayment, RepaymentResponse.class);
        result.setBalance(response.getBalance());


        return result;
    }

    private Boolean isRepayableApplication(Long applicationId) {
        Optional<LoanApplication> existed = loanApplicationRepository.findById(applicationId);
        if (existed.isEmpty()) {
            return false;
        }
        if (existed.get().getContractedAt() == null) {
            return false;
        }

        Optional<Entry> existedEntry = entryRepository.findByApplicationId(applicationId);
        return existedEntry.isPresent();

    }

    @Override
    public List<RepaymentListResponse> get(Long applicationId) {

        List<Repayment> repayments = repaymentRepository.findAllByApplicationId(applicationId);

        return repayments.stream()
                .map(repayment ->
                        modelMapper.map(repayment, RepaymentListResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UpdateRepaymentResponse update(Long repaymentId, RepaymentRequest request) {

        Repayment repayment = repaymentRepository.findById(repaymentId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));
        // 상환 정보가 잘못 기입된 경우 >
        // 500만원 잔고에 상환금 100 인줄 알았으나 잘못됨 다시 원복
        BigDecimal before = repayment.getRepayAmount();
        balanceService.repaymentBalance(repayment.getApplicationId(), BalanceDto.BalanceRepaymentRequest.builder()
                .repaymentAmount(before)
                .repaymentType(RepaymentType.ADD)
                .build());

        repayment.setRepayAmount(request.getRepaymentAmount());
        repaymentRepository.save(repayment);

        BalanceResponse result = balanceService.repaymentBalance(repayment.getApplicationId(), BalanceDto.BalanceRepaymentRequest.builder()
                .repaymentAmount(request.getRepaymentAmount())
                .repaymentType(RepaymentType.REMOVE)
                .build());
        return UpdateRepaymentResponse.builder()
                .applicationId(repayment.getApplicationId())
                .beforeRepaymentAmount(before)
                .afterRepaymentAmount(request.getRepaymentAmount())
                .balance(result.getBalance())
                .build();
    }

    @Override
    public void delete(Long repaymentId) {
        Repayment repayment = repaymentRepository.findById(repaymentId).orElseThrow(() -> new BaseException(ResultType.SYSTEM_ERROR));
        Long applicationId = repayment.getApplicationId();
        BigDecimal removerBalance = repayment.getRepayAmount();
        balanceService.repaymentBalance(applicationId, BalanceDto.BalanceRepaymentRequest.builder()
                .repaymentAmount(removerBalance)
                .repaymentType(RepaymentType.ADD)
                .build());

        repayment.setIsDeleted(true);
        repaymentRepository.save(repayment);

    }
}
