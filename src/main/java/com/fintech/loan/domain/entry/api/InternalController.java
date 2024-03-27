package com.fintech.loan.domain.entry.api;

import com.fintech.loan.domain.AbstractController;
import com.fintech.loan.domain.entry.application.EntryService;
import com.fintech.loan.domain.entry.dto.EntryDto;
import com.fintech.loan.global.dto.ResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.fintech.loan.domain.entry.dto.EntryDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/applications")
public class InternalController extends AbstractController {

    private final EntryService entryService;

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

}
