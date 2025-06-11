package com.timvero.loanschedule.application.dto;

import com.timvero.loanschedule.domain.loan.model.values.LoanType;
import com.timvero.loanschedule.domain.schedule.model.ScheduleType;
import lombok.Builder;

/**
 * LoanRequest is a record that represents a request for a loan calculation.
 * It contains the loan amount, interest rate, and term in months.
 * The fields are validated to ensure they are not null and meet certain criteria.
 */
@Builder
public record LoanRequestAppDto(
        Double amount,
        Double interestRate,
        Integer termInMonths,
        LoanType loanType,
        ScheduleType scheduleType
) {}
