package com.fintech.loan.domain.loanApplication.repository;

import com.fintech.loan.domain.loanApplication.domain.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication,Long> {
}
