package com.timvero.loanschedule.domain.shared.port.out.persistance;

import lombok.Builder;

import java.util.List;

@Builder
public record LoanPersistenceResult(
        long loanId,
        double loanAmount,
        double interestRate,
        double monthlyInterestRate,
        int termInMonths,
        double monthlyPayment,
        double totalPayment,
        double totalInterest,
        List<LoanPaymentsDetailsPersistenceResult> paymentDetails) {
}
