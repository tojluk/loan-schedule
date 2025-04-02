package com.timvero.loanschedule.service.loan;

import com.timvero.loanschedule.dto.LoanParameters;
import com.timvero.loanschedule.dto.LoanRequest;
import org.springframework.stereotype.Component;
/**
 * The Class AnnuityLoanCalculator.
 * This class implements the LoanCalculator interface to calculate loan parameters using the annuity method.
 */
@Component("annuityLoanCalculator")
public class AnnuityLoanCalculator implements LoanCalculator {

    @Override
    public LoanParameters calculateLoanParameters(LoanRequest request) {
        double loanAmount = request.amount();
        double annualInterestRate = request.interestRate();
        int termInMonths = request.termInMonths();
        double monthlyInterestRate = annualInterestRate / 100 / 12;
        double monthlyPayment = loanAmount *
                                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, termInMonths)) /
                                (Math.pow(1 + monthlyInterestRate, termInMonths) - 1);
        double totalPayment = monthlyPayment * termInMonths;
        double totalInterest = totalPayment - loanAmount;
        return new LoanParameters(
                loanAmount,
                annualInterestRate,
                monthlyInterestRate,
                termInMonths,
                monthlyPayment,
                totalPayment,
                totalInterest
        );
    }
}
