package com.fintech.loan.domain.terms.repository;

import com.fintech.loan.domain.terms.domain.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms,Long> {
}
