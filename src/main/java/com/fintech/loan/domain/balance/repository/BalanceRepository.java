package com.fintech.loan.domain.balance.repository;

import com.fintech.loan.domain.balance.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

    Optional<Balance> findByApplicationId(Long applicationId);
}
