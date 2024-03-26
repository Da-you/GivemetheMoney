package com.fintech.loan.domain.loanApplication.application;

import com.fintech.loan.domain.loanApplication.domain.LoanApplication;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationRequest;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationResponse;
import com.fintech.loan.domain.loanApplication.repository.LoanApplicationRepository;
import com.fintech.loan.global.exception.BaseException;
import com.fintech.loan.global.exception.ResultType;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
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
}
