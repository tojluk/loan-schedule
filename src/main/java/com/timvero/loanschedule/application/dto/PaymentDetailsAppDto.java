package com.timvero.loanschedule.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
/**
 * PaymentDetailDto is a record that represents the details of a payment in a loan schedule.
 * It includes information such as payment number, date, amount, principal amount,
 * interest amount, and remaining balance.
 */
@Builder
public record PaymentDetailsAppDto(
        @Schema(description = "Unique identifier for the payment", example = "12345")
        long paymentDetailId,
        @Schema(description = "Loan ID", example = "67890")
        long loanId,
        @Schema(description = "Payment number", example = "1")
        int paymentNumber,
        @Schema(description = "Payment date", example = "2023-10-01")
        LocalDate paymentDate,
        @Schema(description = "Payment amount", example = "1000.00")
        double paymentAmount,
        @Schema(description = "Principal amount", example = "800.00")
        double principalAmount,
        @Schema(description = "Interest amount", example = "200.00")
        double interestAmount,
        @Schema(description = "Remaining balance", example = "20000.00")
        double remainingBalance
) {}
