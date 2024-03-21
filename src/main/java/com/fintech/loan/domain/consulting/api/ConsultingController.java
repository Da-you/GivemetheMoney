package com.fintech.loan.domain.consulting.api;

import com.fintech.loan.domain.AbstractController;
import com.fintech.loan.domain.consulting.application.ConsultingService;
import com.fintech.loan.domain.consulting.dto.ConsultingDto.ConsultingRequest;
import com.fintech.loan.domain.consulting.dto.ConsultingDto.ConsultingResponse;
import com.fintech.loan.global.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultations")
public class ConsultingController extends AbstractController {

    private final ConsultingService consultingService;

    @PostMapping
    public ResponseDTO<ConsultingResponse> create(@RequestBody ConsultingRequest request) {
        return ok(consultingService.create(request));
    }

    @GetMapping("/{consulId}")
    public ResponseDTO<ConsultingResponse> get(@PathVariable Long consulId) {
        return ok(consultingService.get(consulId));
    }

    @PatchMapping("/{consulId}")
    public ResponseDTO<ConsultingResponse> update(@PathVariable Long consulId, @RequestBody ConsultingRequest request) {
        return ok(consultingService.update(consulId, request));
    }

    @DeleteMapping("/{consulId}")
    public void delete(@PathVariable Long consulId) {
        consultingService.delete(consulId);
    }
}
