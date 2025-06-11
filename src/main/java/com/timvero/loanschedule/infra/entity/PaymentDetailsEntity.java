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
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Table("payment_details")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetailsEntity {

    @Id
    long paymentDetailId;
    long loanId;
    int paymentNumber;
    LocalDate paymentDate;
    double paymentAmount;
    double principalAmount;
    double interestAmount;
    double remainingBalance;
    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;
    @Version
    long version;

}
