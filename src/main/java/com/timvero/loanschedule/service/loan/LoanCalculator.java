package com.timvero.loanschedule.service.loan;

import com.timvero.loanschedule.dto.LoanParameters;
import com.timvero.loanschedule.dto.LoanRequest;
import com.timvero.loanschedule.service.type.LoanType;

/**
 * The Interface LoanCalculator.
 */
public interface LoanCalculator {
    LoanType getLoanType();
    LoanParameters calculateLoanParameters(LoanRequest request);
}
