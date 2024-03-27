package com.fintech.loan.domain.entry.api;

import com.fintech.loan.domain.AbstractController;
import com.fintech.loan.domain.entry.application.EntryService;
import com.fintech.loan.domain.repayment.application.RepaymentService;
import com.fintech.loan.domain.repayment.dto.RepaymentDto;
import com.fintech.loan.global.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fintech.loan.domain.entry.dto.EntryDto.*;
import static com.fintech.loan.domain.repayment.dto.RepaymentDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/applications")
public class InternalController extends AbstractController {

    private final EntryService entryService;
    private final RepaymentService repaymentService;

    @PostMapping("/{applicationId}/entries")
    public ResponseDTO<EntryResponse> create(@PathVariable Long applicationId, @RequestBody EntryRequest request) {
        return ok(entryService.create(applicationId, request));
    }

    @GetMapping("/{applicationId}/entries")
    public ResponseDTO<EntryResponse> get(@PathVariable Long applicationId) {
        return ok(entryService.get(applicationId));
    }

    @PatchMapping("/entries/{entryId}")
    public ResponseDTO<UpdateEntryResponse> update(@PathVariable Long entryId, @RequestBody EntryRequest request) {
        return ok(entryService.update(entryId, request));
    }

    @DeleteMapping("/entries/{entryId}")
    public ResponseDTO<Void> delete(@PathVariable Long entryId) {
        entryService.delete(entryId);
        return ok();
    }

    @PostMapping("/{applicationId}/repayments")
    public ResponseDTO<RepaymentResponse> create(@PathVariable Long applicationId, @RequestBody RepaymentRequest request) {
        return ok(repaymentService.create(applicationId, request));
    }

    @GetMapping("/{applicationId}/repayments")
    public ResponseDTO<List<RepaymentListResponse>> getPayments(@PathVariable Long applicationId) {
        return ok(repaymentService.get(applicationId));
    }

    @PatchMapping("/repayments/{repaymentId}")
    public ResponseDTO<UpdateRepaymentResponse> updateRepay(@PathVariable Long repaymentId, @RequestBody RepaymentRequest request) {
        return ok(repaymentService.update(repaymentId, request));
    }

    @DeleteMapping("/repayments/{repaymentId}")
    public ResponseDTO<Void> deleteRepay(@PathVariable Long repaymentId) {
        repaymentService.delete(repaymentId);
        return ok();
    }


}
