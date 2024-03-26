package com.fintech.loan.domain.terms.application;

import com.fintech.loan.domain.terms.domain.Terms;
import com.fintech.loan.domain.terms.dto.TermsDto.TermsRequest;
import com.fintech.loan.domain.terms.dto.TermsDto.TermsResponse;
import com.fintech.loan.domain.terms.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

    private final ModelMapper modelMapper;

    private final TermsRepository termsRepository;

    @Override
    public TermsResponse create(TermsRequest request) {
        Terms terms = modelMapper.map(request, Terms.class);
        return modelMapper.map(termsRepository.save(terms), TermsResponse.class);
    }

    @Override
    public List<TermsResponse> getAllTerms() {

        List<Terms> termsList = termsRepository.findAll();

        return termsList.stream()
                .map(t -> modelMapper.map(t, TermsResponse.class))
                .collect(Collectors.toList());
    }
}
