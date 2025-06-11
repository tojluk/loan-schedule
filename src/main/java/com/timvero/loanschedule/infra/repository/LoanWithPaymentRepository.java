package com.timvero.loanschedule.infra.repository;

import com.timvero.loanschedule.infra.entity.LoanEntity;
import com.timvero.loanschedule.infra.entity.PaymentDetailsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LoanWithPaymentRepository {

    private final LoanRepository loanRepository;
    private final PaymentDetailsRepository paymentRepository;

    public Mono<LoanEntity> saveLoanWithDetails(LoanEntity loan) {
        // First save the loan entity
        return loanRepository.save(loan)
                             .flatMap(savedLoan -> {
                                 // Get the payment details from the transient field
                                 List<PaymentDetailsEntity> details = loan.getPaymentDetails();

                                 if (details == null || details.isEmpty()) {
                                     return Mono.just(savedLoan);
                                 }

                                 // Set the loan ID for each payment detail
                                 details.forEach(detail -> detail.setLoanId(savedLoan.getLoanId()));

                                 // Save all payment details
                                 return paymentRepository.saveAll(details)
                                                         .collectList()
                                                         .map(savedDetails -> {
                                                             // Update the transient field with saved details
                                                             savedLoan.setPaymentDetails(savedDetails);
                                                             return savedLoan;
                                                         });
                             });
    }

}
