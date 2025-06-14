package com.timvero.loanschedule.domain.loan.port.in;

import lombok.Builder;

@Builder
public record LoanResultDomain(
        double loanAmount,
        double annualInterestRate,
        double monthlyInterestRate,
        int termInMonths,
        double monthlyPayment,
        double totalPayment,
        double totalInterest)
{}
