package com.timvero.loanschedule.service.schedule;

import com.timvero.loanschedule.dto.LoanParameters;
import com.timvero.loanschedule.dto.PaymentDetail;

import java.util.List;
/**
 * Interface for generating a payment schedule based on loan parameters.
 */
public interface ScheduleGenerator {
    List<PaymentDetail> generatePaymentSchedule(LoanParameters loanParameters);
}
