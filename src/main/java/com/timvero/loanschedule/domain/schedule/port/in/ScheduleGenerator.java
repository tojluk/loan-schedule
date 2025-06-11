package com.timvero.loanschedule.domain.schedule.port.in;

import com.timvero.loanschedule.domain.schedule.model.ScheduleType;

import java.util.List;
/**
 * Interface for generating a payment schedule based on loan parameters.
 */
public interface ScheduleGenerator {
    ScheduleType getScheduleType();
    List<LoanScheduleResultDomain> generatePaymentSchedule(LoanScheduleCommand loanParameters);
}
