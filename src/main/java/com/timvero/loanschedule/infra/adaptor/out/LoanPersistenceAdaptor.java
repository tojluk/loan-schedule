package com.timvero.loanschedule.infra.adaptor.out;

import com.timvero.loanschedule.application.mapper.LoanMapper;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistenceCommand;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistencePort;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistenceResult;
import com.timvero.loanschedule.infra.entity.LoanEntity;
import com.timvero.loanschedule.infra.repository.LoanWithPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class LoanPersistenceAdaptor implements LoanPersistencePort {

    private final LoanWithPaymentRepository loanWithPaymentRepository;

    @Override
    public Mono<LoanPersistenceResult> saveLoan(LoanPersistenceCommand command) {
        LoanEntity loanEntity = LoanMapper.mapLoanPersistenceCommandToLoanEntity(command);
        return loanWithPaymentRepository.saveLoanWithDetails(loanEntity)
                                        .map(LoanMapper::mapLoanEntityToLoanPersistenceResult);
    }

}
