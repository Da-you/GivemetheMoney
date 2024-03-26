    package com.fintech.loan.domain.terms.application;

    import com.fintech.loan.domain.terms.dto.TermsDto.TermsRequest;
    import com.fintech.loan.domain.terms.dto.TermsDto.TermsResponse;

    import java.util.List;

    public interface TermsService {

        TermsResponse create(TermsRequest request);

        List<TermsResponse> getAllTerms();
    }
