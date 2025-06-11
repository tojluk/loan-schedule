package com.timvero.loanschedule.infra.repository;

import com.timvero.loanschedule.infra.entity.LoanEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends ReactiveCrudRepository<LoanEntity, Long> {

}
