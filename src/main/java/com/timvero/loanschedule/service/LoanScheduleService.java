package com.timvero.loanschedule.service;

import com.timvero.loanschedule.dto.LoanParameters;
import com.timvero.loanschedule.dto.LoanRequest;
import com.timvero.loanschedule.dto.LoanResponse;
import com.timvero.loanschedule.dto.PaymentDetail;
import com.timvero.loanschedule.service.loan.LoanCalculator;
import com.timvero.loanschedule.service.schedule.ScheduleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Service class for calculating loan schedules.
 * It uses a LoanCalculator to calculate loan parameters and a ScheduleGenerator to generate the payment schedule.
 */
@Service
public class LoanScheduleService {

    //TODO add map with types of loans and schedules
    private final LoanCalculator annuityLoanCalculator;
    private final ScheduleGenerator monthlyScheduleGenerator;

    @Autowired
    public LoanScheduleService(@Qualifier("annuityLoanCalculator") LoanCalculator annuityLoanCalculator,
                               @Qualifier("monthlyPaymentScheduleGenerator") ScheduleGenerator monthlyScheduleGenerator) {
        this.annuityLoanCalculator = annuityLoanCalculator;
        this.monthlyScheduleGenerator = monthlyScheduleGenerator;
    }

    /**
     * Calculates the loan schedule based on the provided loan request.
     *
     * @param loanRequest {@link LoanRequest} The loan request containing loan details.
     * @return A Mono containing the LoanResponse with calculated parameters and payment details.
     */
    public Mono<LoanResponse> calculateLoanSchedule(LoanRequest loanRequest) {
        return Mono.fromCallable(() -> {

            LoanParameters parameters = annuityLoanCalculator.calculateLoanParameters(loanRequest);
            List<PaymentDetail> paymentDetails = monthlyScheduleGenerator.generatePaymentSchedule(parameters);

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
