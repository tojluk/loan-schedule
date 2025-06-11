package com.timvero.loanschedule.domain.loan.model;

import com.timvero.loanschedule.domain.loan.model.values.LoanType;
import com.timvero.loanschedule.domain.loan.port.in.LoanResultDomain;
import lombok.experimental.SuperBuilder;

/**
 * The Class AnnuityLoan.
 * This class implements the Loan interface to calculate loan parameters using the annuity method.
 */
@SuperBuilder
public class AnnuityLoan extends Loan {

    @Override
    public LoanResultDomain calculateLoanParameters() {
        double loanAmount = getAmount().value();
        double annualInterestRate = getInterestRate().value();
        int termInMonths = getTerm().value();
        double monthlyInterestRate = annualInterestRate / 100 / 12;
        double monthlyPayment = loanAmount *
                                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, termInMonths)) /
                                (Math.pow(1 + monthlyInterestRate, termInMonths) - 1);
        double totalPayment = monthlyPayment * termInMonths;
        double totalInterest = totalPayment - loanAmount;
        return new LoanResultDomain(
                loanAmount,
                annualInterestRate,
                monthlyInterestRate,
                termInMonths,
                monthlyPayment,
                totalPayment,
                totalInterest);
    }

    @Override
    public LoanType getLoanType() {
        return LoanType.ANNUITY;
    }
}
