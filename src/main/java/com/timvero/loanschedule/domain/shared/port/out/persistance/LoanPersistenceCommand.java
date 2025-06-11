package com.timvero.loanschedule.domain.shared.port.out.persistance;

import com.timvero.loanschedule.domain.loan.port.in.LoanResultDomain;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResultDomain;
import lombok.Builder;

import java.util.List;

@Builder
public record LoanPersistenceCommand(
        double loanAmount,
        double annualInterestRate,
        double monthlyInterestRate,
        int termInMonths,
        double monthlyPayment,
        double totalPayment,
        double totalInterest,
        List<LoanPaymentsDetailsPersistenceCommand> loanSchedule) {
    public LoanPersistenceCommand {
        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero.");
        }
        if (annualInterestRate < 0) {
            throw new IllegalArgumentException("Annual interest rate cannot be negative.");
        }
        if (termInMonths <= 0) {
            throw new IllegalArgumentException("Term in months must be greater than zero.");
        }
    }

    public static LoanPersistenceCommand of(
            LoanResultDomain loanResultDomain,
            List<LoanScheduleResultDomain> loanScheduleResultDomains) {
        return LoanPersistenceCommand.builder()
                .loanAmount(loanResultDomain.loanAmount())
                .annualInterestRate(loanResultDomain.annualInterestRate())
                .monthlyInterestRate(loanResultDomain.monthlyInterestRate())
                .termInMonths(loanResultDomain.termInMonths())
                .monthlyPayment(loanResultDomain.monthlyPayment())
                .totalPayment(loanResultDomain.totalPayment())
                .totalInterest(loanResultDomain.totalInterest())
                .loanSchedule(
                        loanScheduleResultDomains.stream()
                                .map(LoanPaymentsDetailsPersistenceCommand::of)
                                .toList()
                )
                .build();
    }
}
