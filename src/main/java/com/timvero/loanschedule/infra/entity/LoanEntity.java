package com.timvero.loanschedule.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Table("loans")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity {

    @Id
    long loanId;
    double loanAmount;
    double interestRate;
    int termInMonths;
    double monthlyPayment;
    double totalInterest;
    double totalPayment;
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;
    @Version
    long version;

    @Transient
    private List<PaymentDetailsEntity> paymentDetails = new ArrayList<>();

}
