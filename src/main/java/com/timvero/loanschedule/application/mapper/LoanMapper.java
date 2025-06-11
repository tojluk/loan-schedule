package com.timvero.loanschedule.application.mapper;

import com.timvero.loanschedule.application.dto.LoanRequestAppDto;
import com.timvero.loanschedule.application.dto.LoanResponseAppDto;
import com.timvero.loanschedule.application.dto.PaymentDetailsAppDto;
import com.timvero.loanschedule.domain.loan.port.in.LoanCommand;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPaymentsDetailsPersistenceResult;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistenceCommand;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistenceResult;
import com.timvero.loanschedule.infra.entity.LoanEntity;
import com.timvero.loanschedule.infra.entity.PaymentDetailsEntity;
import lombok.experimental.UtilityClass;

/**
 * Utility class for mapping loan-related data between different layers of the application.
 * This class provides methods to convert LoanRequest to LoanCommand, LoanResultDomain to LoanScheduleCommand,
 * and LoanResultDomain to LoanResponse with associated payment details.
 */
@UtilityClass
public class LoanMapper {

    public static LoanCommand mapLoanRequestToLoanCommand(LoanRequestAppDto loanRequest) {
        return LoanCommand.builder()
                .amount(loanRequest.amount())
                .interestRate(loanRequest.interestRate())
                .termInMonths(loanRequest.termInMonths())
                .loanType(loanRequest.loanType())
                .scheduleType(loanRequest.scheduleType())
                .build();
    }

    public static LoanResponseAppDto mapLoanPersistenceResultToLoanResponse(LoanPersistenceResult loanPersistenceResult) {
        return LoanResponseAppDto.builder()
                .loanId(loanPersistenceResult.loanId())
                .loanAmount(loanPersistenceResult.loanAmount())
                .interestRate(loanPersistenceResult.interestRate())
                .monthlyPayment(loanPersistenceResult.monthlyPayment())
                .totalPayment(loanPersistenceResult.totalPayment())
                .totalInterest(loanPersistenceResult.totalInterest())
                .termInMonths(loanPersistenceResult.termInMonths())
                .paymentDetails(loanPersistenceResult.paymentDetails().stream()
                                                     .map(paymentDetail -> PaymentDetailsAppDto.builder()
                                .paymentDetailId(paymentDetail.loanPaymentId())
                                .loanId(paymentDetail.loanId())
                                .paymentDate(paymentDetail.paymentDate())
                                .interestAmount(paymentDetail.interestAmount())
                                .paymentAmount(paymentDetail.paymentAmount())
                                .remainingBalance(paymentDetail.remainingBalance())
                                .principalAmount(paymentDetail.principalAmount())
                                .paymentNumber(paymentDetail.paymentNumber())
                                .build())
                                                     .toList())
                .build();
    }

    public static LoanEntity mapLoanPersistenceCommandToLoanEntity(LoanPersistenceCommand command) {
        return LoanEntity.builder()
                .loanAmount(command.loanAmount())
                .interestRate(command.annualInterestRate())
                .monthlyPayment(command.monthlyPayment())
                .totalPayment(command.totalPayment())
                .totalInterest(command.totalInterest())
                .termInMonths(command.termInMonths())
                .paymentDetails(command.loanSchedule().stream()
                                       .map(paymentDetail -> PaymentDetailsEntity.builder()
                                               .paymentDate(paymentDetail.paymentDate())
                                               .interestAmount(paymentDetail.interestAmount())
                                               .paymentAmount(paymentDetail.paymentAmount())
                                               .remainingBalance(paymentDetail.remainingBalance())
                                               .principalAmount(paymentDetail.principalAmount())
                                               .paymentNumber(paymentDetail.paymentNumber())
                                               .build())
                                       .toList())
                .build();
    }

    public static LoanPersistenceResult mapLoanEntityToLoanPersistenceResult(LoanEntity loanWithPaymentEntity) {
        return LoanPersistenceResult.builder()
                .loanId(loanWithPaymentEntity.getLoanId())
                .loanAmount(loanWithPaymentEntity.getLoanAmount())
                .interestRate(loanWithPaymentEntity.getInterestRate())
                .monthlyPayment(loanWithPaymentEntity.getMonthlyPayment())
                .totalPayment(loanWithPaymentEntity.getTotalPayment())
                .totalInterest(loanWithPaymentEntity.getTotalInterest())
                .termInMonths(loanWithPaymentEntity.getTermInMonths())
                .paymentDetails(loanWithPaymentEntity.getPaymentDetails().stream()
                                                   .map(paymentDetail -> LoanPaymentsDetailsPersistenceResult.builder()
                                                           .loanPaymentId(paymentDetail.getPaymentDetailId())
                                                           .loanId(paymentDetail.getLoanId())
                                                           .paymentDate(
                                                                   paymentDetail.getPaymentDate())
                                                           .interestAmount(
                                                                   paymentDetail.getInterestAmount())
                                                           .paymentAmount(
                                                                   paymentDetail.getPaymentAmount())
                                                           .remainingBalance(
                                                                   paymentDetail.getRemainingBalance())
                                                           .principalAmount(
                                                                   paymentDetail.getPrincipalAmount())
                                                           .paymentNumber(
                                                                   paymentDetail.getPaymentNumber())
                                                           .build())
                                                   .toList())
                .build();
    }

}
