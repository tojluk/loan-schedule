package com.timvero.loanschedule.domain.schedule.port.in;

import com.timvero.loanschedule.domain.loan.port.in.LoanResultDomain;
import lombok.Builder;

/**
 * LoanParameters is a record that holds the parameters of a loan.
 * It includes the loan amount, annual interest rate, monthly interest rate,
 * term in months, monthly payment, total payment, and total interest.
 */
@Builder
public record LoanScheduleCommand(
    double loanAmount,
    double annualInterestRate,
    double monthlyInterestRate,
    int termInMonths,
    double monthlyPayment,
    double totalPayment,
    double totalInterest
) {

    public static LoanScheduleCommand of(LoanResultDomain loanResult) {
            return LoanScheduleCommand.builder()
                    .loanAmount(loanResult.loanAmount())
                    .annualInterestRate(loanResult.annualInterestRate())
                    .monthlyInterestRate(loanResult.monthlyInterestRate())
                    .termInMonths(loanResult.termInMonths())
                    .monthlyPayment(loanResult.monthlyPayment())
                    .totalPayment(loanResult.totalPayment())
                    .totalInterest(loanResult.totalInterest())
                    .build();
    }
}
