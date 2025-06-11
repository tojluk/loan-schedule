package com.timvero.loanschedule.domain.schedule.service;

import com.timvero.loanschedule.domain.schedule.model.ScheduleType;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleCommand;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResultDomain;
import com.timvero.loanschedule.domain.schedule.port.in.ScheduleGenerator;
import com.timvero.loanschedule.infra.dto.LoanParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * MonthlyPaymentLoanScheduleGenerator is responsible for generating a monthly payment schedule
 * based on the provided loan parameters.
 */
@Component("monthlyPaymentScheduleGenerator")
@RequiredArgsConstructor
public class MonthlyPaymentLoanScheduleGenerator implements ScheduleGenerator {

    private final Clock clock;

    @Override
    public ScheduleType getScheduleType() {
        return ScheduleType.MONTHLY;
    }

    /**
     * Generates a payment schedule based on the provided loan parameters.
     *
     * @param params {@link LoanParameters} the loan parameters
     * @return a list of payment details for each month
     */
    @Override
    public List<LoanScheduleResultDomain> generatePaymentSchedule(LoanScheduleCommand params) {
        List<LoanScheduleResultDomain> paymentDetails = new ArrayList<>();
        double remainingBalance = params.loanAmount();
        LocalDate currentDate = LocalDate.now(clock);

        for (int i = 1; i <= params.termInMonths(); i++) {
            double interestForMonth = remainingBalance * params.monthlyInterestRate();
            double principalForMonth = params.monthlyPayment() - interestForMonth;
            remainingBalance -= principalForMonth;
            if (i == params.termInMonths()) {
                principalForMonth += remainingBalance;
                remainingBalance = 0;
            }

            LocalDate paymentDate = currentDate.plusMonths(i);
            paymentDetails.add(new LoanScheduleResultDomain(
                    i,
                    paymentDate,
                    params.monthlyPayment(),
                    principalForMonth,
                    interestForMonth,
                    Math.max(0, remainingBalance)
            ));
        }

        return paymentDetails;
    }
}
