package com.timvero.loanschedule.domain.loan.model;

import com.timvero.loanschedule.domain.loan.model.values.InterestRate;
import com.timvero.loanschedule.domain.loan.model.values.LoanAmount;
import com.timvero.loanschedule.domain.loan.model.values.LoanTerm;
import com.timvero.loanschedule.domain.loan.model.values.LoanType;
import com.timvero.loanschedule.domain.loan.port.in.LoanResultDomain;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * The abstract Loan
 */
@RequiredArgsConstructor
@Getter
@SuperBuilder
public abstract class Loan {

    private final LoanAmount amount;
    private final InterestRate interestRate;
    private final LoanTerm term;

    public abstract LoanResultDomain calculateLoanParameters();
    public abstract LoanType getLoanType();

}
