package com.timvero.loanschedule.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
/**
 * LoanRequest is a record that represents a request for a loan calculation.
 * It contains the loan amount, interest rate, and term in months.
 * The fields are validated to ensure they are not null and meet certain criteria.
 */
@Builder
public record LoanRequest(
        @Schema(description = "Loan amount", example = "10000.0")
        @NotNull(message = "Loan amount is required")
        @Positive(message = "Loan amount must be positive")
        Double amount,

        @Schema(description = "Interest rate", example = "5.0")
        @NotNull(message = "Interest rate is required")
        @Positive(message = "Interest rate must be positive")
        Double interestRate,

        @Schema(description = "Loan term in months", example = "12")
        @NotNull(message = "Loan term is required")
        @Min(value = 1, message = "Loan term must be at least 1 month")
        Integer termInMonths
) {}
