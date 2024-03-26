package com.fintech.loan.domain.loanApplication.repository;

import com.fintech.loan.domain.loanApplication.domain.AcceptTerms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcceptTermsRepository extends JpaRepository<AcceptTerms, Long> {
}
