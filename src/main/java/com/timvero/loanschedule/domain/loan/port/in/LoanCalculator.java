package com.timvero.loanschedule.domain.loan.port.in;

/**
 * The Interface Loan.
 */
public interface LoanCalculator {
    LoanResultDomain calculate(LoanCommand request);
}
