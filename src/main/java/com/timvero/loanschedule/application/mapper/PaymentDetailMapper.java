package com.timvero.loanschedule.application.mapper;

import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResult;
import com.timvero.loanschedule.infra.dto.PaymentDetail;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Utility class for mapping loan schedule results to payment details.
 * This class provides a method to convert a list of LoanScheduleResult objects
 * into a list of PaymentDetail objects.
 */
@UtilityClass
public class PaymentDetailMapper {

    public static List<PaymentDetail> mapLoanScheduleResultsToPaymentDetails(List<LoanScheduleResult> loanScheduleResults) {
        return loanScheduleResults.stream()
                                 .map(schedule -> PaymentDetail.builder()
                                         .paymentNumber(schedule.paymentNumber())
                                         .paymentDate(schedule.paymentDate())
                                         .principalAmount(schedule.principalAmount())
                                         .interestAmount(schedule.interestAmount())
                                         .paymentAmount(schedule.paymentAmount())
                                         .remainingBalance(schedule.remainingBalance())
                                         .build(
                                         ))
                                 .toList();
    }
}
