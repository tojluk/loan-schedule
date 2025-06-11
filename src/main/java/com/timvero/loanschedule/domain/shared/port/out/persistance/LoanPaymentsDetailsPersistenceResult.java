package com.timvero.loanschedule.domain.shared.port.out.persistance;

import lombok.Builder;

import java.time.LocalDate;

/**
 * PaymentDetailDto is a record that represents the details of a payment in a loan schedule.
 * It includes information such as payment number, date, amount, principal amount,
 * interest amount, and remaining balance.
 */
@Builder
public record LoanPaymentsDetailsPersistenceResult(
        long loanPaymentId,
        long loanId,
        int paymentNumber,
        LocalDate paymentDate,
        double paymentAmount,
        double principalAmount,
        double interestAmount,
        double remainingBalance
) {}
