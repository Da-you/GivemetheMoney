package com.fintech.loan.domain.entry.repository;

import com.fintech.loan.domain.entry.domain.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntryRepository extends JpaRepository<Entry, Long> {
    Optional<Entry> findByApplicationId(Long applicationId);
}
