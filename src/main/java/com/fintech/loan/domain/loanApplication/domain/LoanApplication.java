package com.fintech.loan.domain.loanApplication.domain;

import com.fintech.loan.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Where(clause = "is_deleted=false")
public class LoanApplication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "app_id")
    private Long id;

    private String name;

    @Column(nullable = false)
    private String cellPhone;

    private String email;

    private BigDecimal interestRate; // 금리
    private BigDecimal fee; // 수수료

    private LocalDateTime maturity; // 만기일

    private BigDecimal hopeAmount; // 희망 대출금

    private LocalDateTime appliedAt; // 선청일

    private BigDecimal approvalAmount;
    private LocalDateTime contractedAt;

}
