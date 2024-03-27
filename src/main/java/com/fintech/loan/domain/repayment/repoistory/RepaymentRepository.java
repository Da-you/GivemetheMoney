package com.fintech.loan.domain.repayment.repoistory;

import com.fintech.loan.domain.repayment.domain.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findAllByApplicationId(Long applicationId);
}
