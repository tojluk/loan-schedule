package com.timvero.loanschedule.fixture;


import com.timvero.loanschedule.infra.dto.LoanRequest;
import com.timvero.loanschedule.domain.loan.model.values.LoanType;
import com.timvero.loanschedule.domain.schedule.model.ScheduleType;

public class LoanRequestData {

    public static LoanRequest.LoanRequestBuilder buildLoanRequestValidSimple() {
        return LoanRequest.builder()
                .amount(10000.0)
                .interestRate(5.0)
                .termInMonths(12)
                .loanType(LoanType.ANNUITY)
                .scheduleType(ScheduleType.MONTHLY);
    }
}
