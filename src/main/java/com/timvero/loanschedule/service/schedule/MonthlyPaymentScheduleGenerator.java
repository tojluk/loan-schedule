package com.timvero.loanschedule.service.schedule;

import com.timvero.loanschedule.dto.LoanParameters;
import com.timvero.loanschedule.dto.PaymentDetail;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * MonthlyPaymentScheduleGenerator is responsible for generating a monthly payment schedule
 * based on the provided loan parameters.
 */
@Component("monthlyPaymentScheduleGenerator")
public class MonthlyPaymentScheduleGenerator implements ScheduleGenerator {

    /**
     * Generates a payment schedule based on the provided loan parameters.
     *
     * @param params {@link LoanParameters} the loan parameters
     * @return a list of payment details for each month
     */
    @Override
    public List<PaymentDetail> generatePaymentSchedule(LoanParameters params) {
        List<PaymentDetail> paymentDetails = new ArrayList<>();
        double remainingBalance = params.loanAmount();
        LocalDate currentDate = LocalDate.now();

        for (int i = 1; i <= params.termInMonths(); i++) {
            double interestForMonth = remainingBalance * params.monthlyInterestRate();
            double principalForMonth = params.monthlyPayment() - interestForMonth;
            remainingBalance -= principalForMonth;
            if (i == params.termInMonths()) {
                principalForMonth += remainingBalance;
                remainingBalance = 0;
            }

            LocalDate paymentDate = currentDate.plusMonths(i);
            paymentDetails.add(new PaymentDetail(
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
