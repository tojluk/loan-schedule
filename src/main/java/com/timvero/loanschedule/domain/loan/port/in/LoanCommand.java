package com.timvero.loanschedule.domain.loan.port.in;

import com.timvero.loanschedule.domain.loan.model.values.LoanType;
import com.timvero.loanschedule.domain.schedule.model.ScheduleType;
import lombok.Builder;

@Builder
public record LoanCommand(
        double amount,
        double interestRate,
        int termInMonths,
        LoanType loanType,
        ScheduleType scheduleType) {}

