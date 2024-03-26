package com.fintech.loan.domain.terms.api;

import com.fintech.loan.domain.AbstractController;
import com.fintech.loan.domain.terms.application.TermsService;
import com.fintech.loan.domain.terms.dto.TermsDto;
import com.fintech.loan.global.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fintech.loan.domain.terms.dto.TermsDto.*;
import static com.fintech.loan.global.dto.ResponseDTO.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/terms")
public class TermsController extends AbstractController {

    private final TermsService termsService;

    @PostMapping
    public ResponseDTO<TermsResponse> create(@RequestBody TermsRequest request){
        return ok(termsService.create(request));
    }

    @GetMapping
    public ResponseDTO<List<TermsResponse>> getAll(){
        return ok(termsService.getAllTerms());
    }
}
