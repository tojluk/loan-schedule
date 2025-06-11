package com.timvero.loanschedule.infra.repository;

import com.timvero.loanschedule.infra.entity.PaymentDetailsEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends ReactiveCrudRepository<PaymentDetailsEntity, Long> {

}
