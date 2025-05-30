package com.timvero.loanschedule.application.factory;

import com.timvero.loanschedule.domain.loan.model.AnnuityLoan;
import com.timvero.loanschedule.domain.loan.model.Loan;
import com.timvero.loanschedule.domain.loan.model.values.InterestRate;
import com.timvero.loanschedule.domain.loan.model.values.LoanAmount;
import com.timvero.loanschedule.domain.loan.model.values.LoanTerm;
import com.timvero.loanschedule.domain.loan.port.in.LoanCommand;
import org.springframework.stereotype.Component;

/**
 * LoanFactory is responsible for creating instances of Loan based on the provided LoanCommand.
 * It currently supports the creation of AnnuityLoan.
 */
@Component
public class LoanFactory {

    public Loan createLoan(LoanCommand command) {
        return switch (command.loanType()) {
            case ANNUITY -> craeteAnnuityLoan(command);
            default -> throw new IllegalArgumentException("Unsupported loan type: " + command.loanType());
        };
    }

    private static AnnuityLoan craeteAnnuityLoan(LoanCommand command) {
        return AnnuityLoan.builder()
                .amount(new LoanAmount(command.amount()))
                .interestRate(new InterestRate(command.interestRate()))
                .term(new LoanTerm(command.termInMonths()))
                .build();
    }
}
