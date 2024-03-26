package com.fintech.loan.domain.judgment.repository;

import com.fintech.loan.domain.judgment.domain.Judgment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JudgmentRepository extends JpaRepository<Judgment, Long> {
    Optional<Judgment> findByApplicationId(Long applicationId);
}
