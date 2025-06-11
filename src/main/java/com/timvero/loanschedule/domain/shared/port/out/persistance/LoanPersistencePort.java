package com.timvero.loanschedule.domain.shared.port.out.persistance;

import reactor.core.publisher.Mono;

public interface LoanPersistencePort {
    Mono<LoanPersistenceResult> saveLoan(LoanPersistenceCommand command);
}
