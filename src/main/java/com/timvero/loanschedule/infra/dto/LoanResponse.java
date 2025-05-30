package com.timvero.loanschedule.infra.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;
/**
 * LoanResponse is a record that represents the response of a loan calculation.
 * It contains information about the loan amount, interest rate, term, monthly payment,
 * total interest, total payment, and a list of payment details.
 */
@Builder
public record LoanResponse(
        @Schema(description = "Loan amount", example = "10000")
        double loanAmount,
        @Schema(description = "Interest rate", example = "5.0")
        double interestRate,
        @Schema(description = "Loan term in months", example = "60")
        int termInMonths,
        @Schema(description = "Monthly payment", example = "188.71")
        double monthlyPayment,
        @Schema(description = "Total interest paid", example = "1322.60")
        double totalInterest,
        @Schema(description = "Total payment", example = "11322.60")
        double totalPayment,
        @Schema(description = "List of payment details")
        List<PaymentDetail> paymentDetails
) {}
