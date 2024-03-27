package com.fintech.loan.domain.judgment.api;

import com.fintech.loan.domain.AbstractController;
import com.fintech.loan.domain.judgment.application.JudgmentService;
import com.fintech.loan.domain.judgment.dto.JudgmentDto;
import com.fintech.loan.domain.judgment.dto.JudgmentDto.JudgmentRequest;
import com.fintech.loan.domain.judgment.dto.JudgmentDto.JudgmentResponse;
import com.fintech.loan.domain.loanApplication.dto.LoanApplicationDto;
import com.fintech.loan.global.dto.ResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/judgments")
@RequiredArgsConstructor
public class JudgmentController extends AbstractController {

    private final JudgmentService judgmentService;

    @PostMapping
    public ResponseDTO<JudgmentResponse> create(@RequestBody JudgmentRequest request) {
        return ok(judgmentService.create(request));
    }

    @GetMapping("/{judgmentId}")
    public ResponseDTO<JudgmentResponse> get(@PathVariable Long judgmentId) {
        return ok(judgmentService.get(judgmentId));
    }

    @GetMapping("/applications/{applicationId}")
    public ResponseDTO<JudgmentResponse> getJudgmentOfApplication(@PathVariable Long applicationId) {
        return ok(judgmentService.getJudgmentOfApplication(applicationId));
    }

    @PatchMapping("/{judgmentId}")
    public ResponseDTO<JudgmentResponse> update(@PathVariable Long judgmentId, @RequestBody JudgmentRequest request) {
        return ok(judgmentService.updateJudgment(judgmentId, request));
    }

    @DeleteMapping("/{judgmentId}")
    public ResponseDTO<Void> delete(@PathVariable Long judgmentId) {
        judgmentService.delete(judgmentId);
        return ok();
    }

    @PatchMapping("/{judgmentId}/grants")
    public ResponseDTO<LoanApplicationDto.GrantAmount> grant(@PathVariable Long judgmentId) {
        return ok(judgmentService.grant(judgmentId)) ;
    }
}
