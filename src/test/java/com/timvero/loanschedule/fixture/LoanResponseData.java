package com.timvero.loanschedule.fixture;


import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResult;
import com.timvero.loanschedule.infra.dto.LoanResponse;
import com.timvero.loanschedule.infra.dto.PaymentDetail;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class LoanResponseData {

    public static List<LoanScheduleResult> buildSampleLoanScheduleResults() {
        return List.of(
                LoanScheduleResult.builder()
                        .paymentNumber(1)
                        .paymentDate(LocalDate.of(2025, 5, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(814.4081512180079)
                        .interestAmount(41.666666666666664)
                        .remainingBalance(9185.591848781993)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(2)
                        .paymentDate(LocalDate.of(2025, 6, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(817.8015185147495)
                        .interestAmount(38.27329936992497)
                        .remainingBalance(8367.790330267244)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(3)
                        .paymentDate(LocalDate.of(2025, 7, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(821.2090248418943)
                        .interestAmount(34.86579304278018)
                        .remainingBalance(7546.5813054253495)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(4)
                        .paymentDate(LocalDate.of(2025, 8, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(824.6307291120689)
                        .interestAmount(31.44408877260562)
                        .remainingBalance(6721.95057631328)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(5)
                        .paymentDate(LocalDate.of(2025, 9, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(828.0666904833691)
                        .interestAmount(28.008127401305334)
                        .remainingBalance(5893.883885829911)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(6)
                        .paymentDate(LocalDate.of(2025, 10, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(831.5169683603832)
                        .interestAmount(24.557849524291296)
                        .remainingBalance(5062.366917469528)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(7)
                        .paymentDate(LocalDate.of(2025, 11, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(834.9816223952182)
                        .interestAmount(21.093195489456367)
                        .remainingBalance(4227.385295074309)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(8)
                        .paymentDate(LocalDate.of(2025, 12, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(838.4607124885316)
                        .interestAmount(17.614105396142957)
                        .remainingBalance(3388.924582585778)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(9)
                        .paymentDate(LocalDate.of(2026, 1, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(841.9542987905671)
                        .interestAmount(14.120519094107408)
                        .remainingBalance(2546.970283795211)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(10)
                        .paymentDate(LocalDate.of(2026, 2, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(845.4624417021945)
                        .interestAmount(10.612376182480045)
                        .remainingBalance(1701.5078420930163)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(11)
                        .paymentDate(LocalDate.of(2026, 3, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(848.9852018759536)
                        .interestAmount(7.089616008720901)
                        .remainingBalance(852.5226402170626)
                        .build(),
                LoanScheduleResult.builder()
                        .paymentNumber(12)
                        .paymentDate(LocalDate.of(2026, 4, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(852.5226402170626)
                        .interestAmount(3.5521776675710943)
                        .remainingBalance(0.0)
                        .build()
        );
    }

    public static LoanResponse.LoanResponseBuilder buildSampleLoanResponse() {
        List<PaymentDetail> paymentDetails = Arrays.asList(
                PaymentDetail.builder()
                        .paymentNumber(1)
                        .paymentDate(LocalDate.of(2025, 5, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(814.4081512180079)
                        .interestAmount(41.666666666666664)
                        .remainingBalance(9185.591848781993)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(2)
                        .paymentDate(LocalDate.of(2025, 6, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(817.8015185147495)
                        .interestAmount(38.27329936992497)
                        .remainingBalance(8367.790330267244)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(3)
                        .paymentDate(LocalDate.of(2025, 7, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(821.2090248418943)
                        .interestAmount(34.86579304278018)
                        .remainingBalance(7546.5813054253495)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(4)
                        .paymentDate(LocalDate.of(2025, 8, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(824.6307291120689)
                        .interestAmount(31.44408877260562)
                        .remainingBalance(6721.95057631328)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(5)
                        .paymentDate(LocalDate.of(2025, 9, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(828.0666904833691)
                        .interestAmount(28.008127401305334)
                        .remainingBalance(5893.883885829911)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(6)
                        .paymentDate(LocalDate.of(2025, 10, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(831.5169683603832)
                        .interestAmount(24.557849524291296)
                        .remainingBalance(5062.366917469528)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(7)
                        .paymentDate(LocalDate.of(2025, 11, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(834.9816223952182)
                        .interestAmount(21.093195489456367)
                        .remainingBalance(4227.385295074309)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(8)
                        .paymentDate(LocalDate.of(2025, 12, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(838.4607124885316)
                        .interestAmount(17.614105396142957)
                        .remainingBalance(3388.924582585778)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(9)
                        .paymentDate(LocalDate.of(2026, 1, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(841.9542987905671)
                        .interestAmount(14.120519094107408)
                        .remainingBalance(2546.970283795211)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(10)
                        .paymentDate(LocalDate.of(2026, 2, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(845.4624417021945)
                        .interestAmount(10.612376182480045)
                        .remainingBalance(1701.5078420930163)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(11)
                        .paymentDate(LocalDate.of(2026, 3, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(848.9852018759536)
                        .interestAmount(7.089616008720901)
                        .remainingBalance(852.5226402170626)
                        .build(),
                PaymentDetail.builder()
                        .paymentNumber(12)
                        .paymentDate(LocalDate.of(2026, 4, 2))
                        .paymentAmount(856.0748178846745)
                        .principalAmount(852.5226402170626)
                        .interestAmount(3.5521776675710943)
                        .remainingBalance(0.0)
                        .build()
        );

        return LoanResponse.builder()
                .loanAmount(10000.0)
                .interestRate(5.0)
                .termInMonths(12)
                .monthlyPayment(856.0748178846745)
                .totalInterest(272.8978146160953)
                .totalPayment(10272.897814616095)
                .paymentDetails(paymentDetails);
    }

}
