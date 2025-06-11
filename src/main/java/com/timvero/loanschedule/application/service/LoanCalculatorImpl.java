package com.timvero.loanschedule.application.service;

import com.timvero.loanschedule.application.factory.LoanFactory;
import com.timvero.loanschedule.domain.loan.port.in.LoanCalculator;
import com.timvero.loanschedule.domain.loan.port.in.LoanCommand;
import com.timvero.loanschedule.domain.loan.port.in.LoanResultDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
/**
 * The Class AnnuityLoan.
 * This class implements the Loan interface to calculate loan parameters using the annuity method.
 */
@Component("loanCalculator")
@RequiredArgsConstructor
public class LoanCalculatorImpl implements LoanCalculator {

    private final LoanFactory loanFactory;

    @Override
    public LoanResultDomain calculate(LoanCommand command) {
        return loanFactory.createLoan(command).calculateLoanParameters();
    }
}
