package com.timvero.loanschedule.dto;

import lombok.Builder;
/**
 * LoanParameters is a record that holds the parameters of a loan.
 * It includes the loan amount, annual interest rate, monthly interest rate,
 * term in months, monthly payment, total payment, and total interest.
 */
@Builder
public record LoanParameters(
    double loanAmount,
    double annualInterestRate,
    double monthlyInterestRate,
    int termInMonths,
    double monthlyPayment,
    double totalPayment,
    double totalInterest
) {}
