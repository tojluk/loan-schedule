package com.timvero.loanschedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
/**
 * PaymentDetail is a record that represents the details of a payment in a loan schedule.
 * It includes information such as payment number, date, amount, principal amount,
 * interest amount, and remaining balance.
 */
@Builder
public record PaymentDetail(
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
