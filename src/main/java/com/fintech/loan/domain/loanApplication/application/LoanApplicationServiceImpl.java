package com.fintech.loan.domain.loanApplication.application;

import com.fintech.loan.domain.loanApplication.domain.AcceptTerms;
import com.fintech.loan.domain.loanApplication.domain.LoanApplication;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationRequest;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationResponse;
import com.fintech.loan.domain.loanApplication.repository.AcceptTermsRepository;
import com.fintech.loan.domain.loanApplication.repository.LoanApplicationRepository;
import com.fintech.loan.domain.terms.domain.Terms;
import com.fintech.loan.domain.terms.repository.TermsRepository;
import com.fintech.loan.global.exception.BaseException;
import com.fintech.loan.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final TermsRepository termsRepository;
    private final AcceptTermsRepository acceptTermsRepository;
    private final ModelMapper modelMapper;


    @Override
    public LoanApplicationResponse create(LoanApplicationRequest request) {
        LoanApplication app = modelMapper.map(request, LoanApplication.class);

        app.setAppliedAt(LocalDateTime.now());

        return modelMapper.map(loanApplicationRepository.save(app), LoanApplicationResponse.class);

    }

    @Override
    public LoanApplicationResponse get(Long applicationId) {

        LoanApplication app = loanApplicationRepository.findById(applicationId).orElseThrow(() ->
                new BaseException(ResultType.SYSTEM_ERROR));

        return modelMapper.map(app, LoanApplicationResponse.class);
    }

    @Override
    public LoanApplicationResponse update(Long applicationId, LoanApplicationRequest request) {
        LoanApplication app = loanApplicationRepository.findById(applicationId).orElseThrow(() ->
                new BaseException(ResultType.SYSTEM_ERROR));

        app.setName(request.getName());
        app.setCellPhone(request.getCellPhone());
        app.setEmail(request.getEmail());
        app.setHopeAmount(request.getHopeAmount());


        return modelMapper.map(loanApplicationRepository.save(app), LoanApplicationResponse.class);
    }

    @Override
    public void delete(Long applicationId) {
        LoanApplication app = loanApplicationRepository.findById(applicationId).orElseThrow(() ->
                new BaseException(ResultType.SYSTEM_ERROR));

        app.setIsDeleted(true);
        loanApplicationRepository.save(app);
    }

    @Override
    public Boolean acceptTerms(Long applicationId, LoanApplicationDto.AcceptTermsDto request) {
        // 대출 신청 정보 확인
        loanApplicationRepository.findById(applicationId).orElseThrow(() ->
                new BaseException(ResultType.SYSTEM_ERROR));
        // 약관 존재 유무
        List<Terms> termsList = termsRepository.findAll(Sort.by(Sort.Direction.ASC, "termsId"));
        if (termsList.isEmpty()) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        // 정렬후 우리가 게시한 약관의 수와 고객이 동의한 약관의 수가 일치해야함
        List<Long> acceptTermsIds = request.getAcceptTermsIds();
        if (termsList.size() != acceptTermsIds.size()) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }

        List<Long> termsIds = termsList.stream().map(Terms::getId).collect(Collectors.toList());
        Collections.sort(acceptTermsIds);
        if (!acceptTermsIds.containsAll(termsIds)) {
            throw new BaseException(ResultType.SYSTEM_ERROR);
        }
        for (Long termsId : acceptTermsIds) {
            AcceptTerms accepted = AcceptTerms.builder()
                    .termsId(termsId)
                    .applicationId(applicationId)
                    .build();

            acceptTermsRepository.save(accepted);
        }

        return true;
    }
}
