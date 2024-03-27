package com.fintech.loan.domain.judgment.application;

import com.fintech.loan.domain.judgment.domain.Judgment;
import com.fintech.loan.domain.judgment.dto.JudgmentDto;
import com.fintech.loan.domain.judgment.dto.JudgmentDto.JudgmentRequest;
import com.fintech.loan.domain.judgment.repository.JudgmentRepository;
import com.fintech.loan.domain.loanApplication.domain.LoanApplication;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.GrantAmount;
import com.fintech.loan.domain.loanApplication.repository.LoanApplicationRepository;
import com.fintech.loan.global.exception.BaseException;
import com.fintech.loan.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

import static com.fintech.loan.domain.judgment.dto.JudgmentDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class JudgmentServiceImpl implements JudgmentService {

    private final JudgmentRepository judgmentRepository;
    private final LoanApplicationRepository loanApplicationRepository;
    private final ModelMapper modelMapper;

    @Override
    public JudgmentResponse create(JudgmentRequest request) {
        // 신청정보 검증
        Long applicationId = request.getApplicationId();
        if (!isPresentApplication(applicationId)) {
            throw new BaseException(ResultType.NOT_EXIST);
        }
        //  DTO > entity > save

        Judgment judgment = modelMapper.map(request, Judgment.class);
        // save > response
        return modelMapper.map(judgmentRepository.save(judgment), JudgmentResponse.class);
    }

    @Override
    public JudgmentResponse get(Long judgmentId) {
        Judgment judgment = judgmentRepository.findById(judgmentId)
                .orElseThrow(() -> new BaseException(ResultType.NOT_EXIST));
        return modelMapper.map(judgment, JudgmentResponse.class);
    }

    @Override
    public JudgmentResponse getJudgmentOfApplication(Long applicationId) {
        if (!isPresentApplication(applicationId)) {
            throw new BaseException(ResultType.NOT_EXIST);
        }

        Judgment judgment = judgmentRepository.findByApplicationId(applicationId).orElseThrow(
                () -> new BaseException(ResultType.SYSTEM_ERROR));

        return modelMapper.map(judgment, JudgmentResponse.class);
    }

    @Override
    public JudgmentResponse updateJudgment(Long judgmentId, JudgmentRequest request) {

        Judgment judgment = judgmentRepository.findById(judgmentId)
                .orElseThrow(() -> new BaseException(ResultType.NOT_EXIST));

        judgment.setName(request.getName());
        judgment.setApprovalAmount(request.getApprovalAmount());

        return modelMapper.map(judgmentRepository.save(judgment), JudgmentResponse.class);
    }

    @Override
    public void delete(Long judgmentId) {
        Judgment judgment = judgmentRepository.findById(judgmentId)
                .orElseThrow(() -> new BaseException(ResultType.NOT_EXIST));
        judgment.setIsDeleted(true);
        judgmentRepository.save(judgment);
    }

    @Override
    public GrantAmount grant(Long judgmentId) {
        Judgment judgment = judgmentRepository.findById(judgmentId)
                .orElseThrow(() -> new BaseException(ResultType.NOT_EXIST));

        Long applicationId = judgment.getApplicationId();
        LoanApplication loanApplication = loanApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new BaseException(ResultType.NOT_EXIST));

        loanApplication.setApprovalAmount(judgment.getApprovalAmount());
        loanApplicationRepository.save(loanApplication);

        return modelMapper.map(loanApplication, GrantAmount.class);
    }

    private Boolean isPresentApplication(Long applicationId) {
        return loanApplicationRepository.findById(applicationId).isPresent();
    }


}
