package com.timvero.loanschedule.application.mapper;

import com.timvero.loanschedule.domain.loan.port.in.LoanCommand;
import com.timvero.loanschedule.domain.loan.port.in.LoanResult;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleCommand;
import com.timvero.loanschedule.infra.dto.LoanRequest;
import com.timvero.loanschedule.infra.dto.LoanResponse;
import com.timvero.loanschedule.infra.dto.PaymentDetail;
import lombok.experimental.UtilityClass;

import java.util.List;
/**
 * Utility class for mapping loan-related data between different layers of the application.
 * This class provides methods to convert LoanRequest to LoanCommand, LoanResult to LoanScheduleCommand,
 * and LoanResult to LoanResponse with associated payment details.
 */
@UtilityClass
public class LoanMapper {

    public static LoanCommand mapLoanRequestToLoanCommand(LoanRequest loanRequest) {
        return LoanCommand.builder()
                .amount(loanRequest.amount())
                .interestRate(loanRequest.interestRate())
                .termInMonths(loanRequest.termInMonths())
                .loanType(loanRequest.loanType())
                .scheduleType(loanRequest.scheduleType())
                .build();
    }

    public static LoanScheduleCommand mapLoanResultToLoanScheduleCommand(LoanResult loanResult) {
        return LoanScheduleCommand.builder()
                .loanAmount(loanResult.loanAmount())
                .annualInterestRate(loanResult.annualInterestRate())
                .monthlyInterestRate(loanResult.monthlyInterestRate())
                .termInMonths(loanResult.termInMonths())
                .monthlyPayment(loanResult.monthlyPayment())
                .totalPayment(loanResult.totalPayment())
                .totalInterest(loanResult.totalInterest())
                .build();
    }

    public static LoanResponse mapLoanResultToLoanResponse(LoanResult loanResult, List<PaymentDetail> paymentDetails) {
        return LoanResponse.builder()
                        .loanAmount(loanResult.loanAmount())
                        .paymentDetails(paymentDetails)
                        .interestRate(loanResult.annualInterestRate())
                        .termInMonths(loanResult.termInMonths())
                        .monthlyPayment(loanResult.monthlyPayment())
                        .totalInterest(loanResult.totalInterest())
                        .totalPayment(loanResult.totalPayment())
                        .build();
    }
}
