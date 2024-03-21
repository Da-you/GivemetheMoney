package com.fintech.loan.domain.consulting.domain;

import com.fintech.loan.domain.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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
public class Consulting extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consulting_id", nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime appliedAt;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cellPhone;

    private String email;

    private String memo;

    private String address;

    private String city;

    private String zipCode;

}
