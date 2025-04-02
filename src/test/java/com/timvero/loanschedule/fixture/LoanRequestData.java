package com.timvero.loanschedule.fixture;


import com.timvero.loanschedule.dto.LoanRequest;

public class LoanRequestData {

    public static LoanRequest.LoanRequestBuilder buildLoanRequestValidSimple() {
        return LoanRequest.builder()
                .amount(10000.0)
                .interestRate(5.0)
                .termInMonths(12);
    }
}
