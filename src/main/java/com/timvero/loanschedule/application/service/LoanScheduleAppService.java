package com.timvero.loanschedule.application.service;

import com.timvero.loanschedule.application.mapper.LoanMapper;
import com.timvero.loanschedule.application.mapper.PaymentDetailMapper;
import com.timvero.loanschedule.application.registry.LoanScheduleRegistry;
import com.timvero.loanschedule.domain.loan.port.in.LoanCalculator;
import com.timvero.loanschedule.domain.loan.port.in.LoanResult;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleCommand;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResult;
import com.timvero.loanschedule.domain.shared.event.LoanScheduleCalculatedEvent;
import com.timvero.loanschedule.domain.shared.port.out.DomainEventPublisher;
import com.timvero.loanschedule.infra.dto.LoanRequest;
import com.timvero.loanschedule.infra.dto.LoanResponse;
import com.timvero.loanschedule.infra.dto.PaymentDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

/**
 * Service class for calculating loan schedules.
 * It uses a Loan to calculate loan parameters and a ScheduleGenerator to generate the payment schedule.
 */
@Service
@RequiredArgsConstructor
public class LoanScheduleAppService {

    private final LoanCalculator loanCalculator ;
    private final LoanScheduleRegistry loanScheduleRegistry;
    private final DomainEventPublisher domainEventPublisher;

    /**
     * Calculates the loan schedule based on the provided loan request.
     *
     * @param loanRequest {@link LoanRequest} The loan request containing loan details.
     * @return A Mono containing the LoanResponse with calculated parameters and payment details.
     */
    public Mono<LoanResponse> calculateLoanSchedule(LoanRequest loanRequest) {
        return Mono.fromCallable(() -> {
            LoanResult loanResult = loanCalculator.calculate(LoanMapper.mapLoanRequestToLoanCommand(loanRequest));

            LoanScheduleCommand scheduleCommand = LoanMapper.mapLoanResultToLoanScheduleCommand(loanResult);
            List<LoanScheduleResult> loanScheduleResult = loanScheduleRegistry.get(loanRequest.scheduleType())
                                                                              .generatePaymentSchedule(scheduleCommand);

            List<PaymentDetail> paymentDetails = PaymentDetailMapper.mapLoanScheduleResultsToPaymentDetails(loanScheduleResult);

            domainEventPublisher.publishLoanScheduleEvent(new LoanScheduleCalculatedEvent(UUID.randomUUID().toString()));

            return LoanMapper.mapLoanResultToLoanResponse(loanResult, paymentDetails);
        });
    }
}
