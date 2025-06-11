package com.timvero.loanschedule.application.mapper;

import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResultDomain;
import com.timvero.loanschedule.infra.dto.PaymentDetailDto;
import lombok.experimental.UtilityClass;

import java.util.List;

/**
 * Utility class for mapping loan schedule results to payment details.
 * This class provides a method to convert a list of LoanScheduleResultDomain objects
 * into a list of PaymentDetailDto objects.
 */
@UtilityClass
public class PaymentDetailMapper {

    public static List<PaymentDetailDto> mapLoanScheduleResultsToPaymentDetails(List<LoanScheduleResultDomain> loanScheduleResults) {
        return loanScheduleResults.stream()
                                 .map(schedule -> PaymentDetailDto.builder()
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
