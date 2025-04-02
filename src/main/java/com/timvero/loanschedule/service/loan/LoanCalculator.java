package com.timvero.loanschedule.service.loan;

import com.timvero.loanschedule.dto.LoanParameters;
import com.timvero.loanschedule.dto.LoanRequest;

/**
 * The Interface LoanCalculator.
 */
public interface LoanCalculator {
    LoanParameters calculateLoanParameters(LoanRequest request);
}
