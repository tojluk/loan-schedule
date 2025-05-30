package com.timvero.loanschedule.domain.loan.port.in;

/**
 * The Interface Loan.
 */
public interface LoanCalculator {
    LoanResult calculate(LoanCommand request);
}
