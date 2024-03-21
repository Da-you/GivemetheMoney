package com.fintech.loan.domain.consulting.repository;

import com.fintech.loan.domain.consulting.domain.Consulting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultingRepository extends JpaRepository<Consulting, Long> {
}
