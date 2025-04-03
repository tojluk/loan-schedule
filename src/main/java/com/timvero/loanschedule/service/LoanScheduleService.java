package com.timvero.loanschedule.service;

import com.timvero.loanschedule.dto.LoanParameters;
import com.timvero.loanschedule.dto.LoanRequest;
import com.timvero.loanschedule.dto.LoanResponse;
import com.timvero.loanschedule.dto.PaymentDetail;
import com.timvero.loanschedule.service.registry.LoanRegistry;
import com.timvero.loanschedule.service.registry.ScheduleRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Service class for calculating loan schedules.
 * It uses a LoanCalculator to calculate loan parameters and a ScheduleGenerator to generate the payment schedule.
 */
@Service
@RequiredArgsConstructor
public class LoanScheduleService {

    private final LoanRegistry loanRegistry;
    private final ScheduleRegistry scheduleRegistry;

    /**
     * Calculates the loan schedule based on the provided loan request.
     *
     * @param loanRequest {@link LoanRequest} The loan request containing loan details.
     * @return A Mono containing the LoanResponse with calculated parameters and payment details.
     */
    public Mono<LoanResponse> calculateLoanSchedule(LoanRequest loanRequest) {
        return Mono.fromCallable(() -> {

            LoanParameters parameters = loanRegistry.get(loanRequest.loanType())
                                                    .calculateLoanParameters(loanRequest);
            List<PaymentDetail> paymentDetails = scheduleRegistry.get(loanRequest.scheduleType())
                                                                 .generatePaymentSchedule(parameters);

            return new LoanResponse(
                    parameters.loanAmount(),
                    parameters.annualInterestRate(),
                    parameters.termInMonths(),
                    parameters.monthlyPayment(),
                    parameters.totalInterest(),
                    parameters.totalPayment(),
                    paymentDetails
            );
        });
    }
}
