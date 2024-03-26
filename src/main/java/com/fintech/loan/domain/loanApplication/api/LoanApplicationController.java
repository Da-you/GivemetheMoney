package com.fintech.loan.domain.loanApplication.api;

import com.fintech.loan.domain.AbstractController;
import com.fintech.loan.domain.loanApplication.application.LoanApplicationService;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationRequest;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto.LoanApplicationResponse;
import com.fintech.loan.global.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/applications")
public class LoanApplicationController extends AbstractController {
    private final LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseDTO<LoanApplicationResponse> create(@RequestBody LoanApplicationRequest request) {
        return ok(loanApplicationService.create(request));
    }

    @GetMapping("/{applicationId}")
    public ResponseDTO<LoanApplicationResponse> get(@PathVariable Long applicationId) {
        return ok(loanApplicationService.get(applicationId));
    }

    @PatchMapping("/{applicationId}")
    public ResponseDTO<LoanApplicationResponse> update(@PathVariable Long applicationId,
                                                       @RequestBody LoanApplicationRequest request) {
        return ok(loanApplicationService.update(applicationId, request));
    }

    @DeleteMapping("/{applicationId}")
    public void delete(@PathVariable Long applicationId) {
        loanApplicationService.delete(applicationId);
    }
}
