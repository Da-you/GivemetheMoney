package com.fintech.loan.domain.entry.application;

import com.fintech.loan.domain.entry.dto.EntryDto;
import com.fintech.loan.domain.entry.dto.EntryDto.EntryResponse;

import static com.fintech.loan.domain.entry.dto.EntryDto.*;

public interface EntryService {

    EntryResponse create(Long applicationId, EntryRequest request);

    EntryResponse get(Long applicationId);

    UpdateEntryResponse update(Long entryId, EntryRequest request);

    void delete(Long entryId);
}
