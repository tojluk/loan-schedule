package com.timvero.loanschedule.application.service;

import com.timvero.loanschedule.application.dto.LoanRequestAppDto;
import com.timvero.loanschedule.application.dto.LoanResponseAppDto;
import reactor.core.publisher.Mono;

/**
 * Service class for calculating loan schedules.
 * It uses a Loan to calculate loan parameters and a ScheduleGenerator to generate the payment schedule.
 */
public interface LoanScheduleAppService {

    Mono<LoanResponseAppDto> calculateLoanSchedule(LoanRequestAppDto loanRequest);

}
