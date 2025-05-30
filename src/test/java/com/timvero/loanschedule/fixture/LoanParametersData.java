package com.timvero.loanschedule.fixture;

import com.timvero.loanschedule.domain.loan.port.in.LoanResult;
import com.timvero.loanschedule.infra.dto.LoanParameters;

public class LoanParametersData {

    public static LoanParameters.LoanParametersBuilder buildLoanParametersSimple() {
        return LoanParameters.builder()
                .loanAmount(10000.0)
                .annualInterestRate(5.0)
                .monthlyInterestRate(5.0 / 12 / 100)
                .termInMonths(12)
                .monthlyPayment(856.0748178846745)
                .totalPayment(10272.897814616095)
                .totalInterest(272.8978146160953);
    }

    public static LoanResult.LoanResultBuilder buildLoanResults() {
        return LoanResult.builder()
                .loanAmount(10000.0)
                .annualInterestRate(5.0)
                .monthlyInterestRate(5.0 / 12 / 100)
                .termInMonths(12)
                .monthlyPayment(856.0748178846745)
                .totalPayment(10272.897814616095)
                .totalInterest(272.8978146160953);
    }
}
