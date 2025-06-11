package com.timvero.loanschedule.application.service;

import com.timvero.loanschedule.application.dto.LoanRequestAppDto;
import com.timvero.loanschedule.application.dto.LoanResponseAppDto;
import com.timvero.loanschedule.application.mapper.LoanMapper;
import com.timvero.loanschedule.application.registry.LoanScheduleRegistry;
import com.timvero.loanschedule.domain.loan.port.in.LoanCalculator;
import com.timvero.loanschedule.domain.loan.port.in.LoanResultDomain;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleCommand;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResultDomain;
import com.timvero.loanschedule.domain.shared.port.out.event.CalculatedDomainEvent;
import com.timvero.loanschedule.domain.shared.port.out.event.DomainEventPublisherPort;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistenceCommand;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistencePort;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistenceResult;
import com.timvero.loanschedule.domain.shared.port.out.schedule.DomainScheduleTaskPort;
import com.timvero.loanschedule.domain.shared.port.out.schedule.NotifyDomainScheduledTask;
import com.timvero.loanschedule.infra.dto.LoanRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Service class for calculating loan schedules.
 * It uses a Loan to calculate loan parameters and a ScheduleGenerator to generate the payment schedule.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class LoanScheduleAppServiceImpl implements LoanScheduleAppService {

    private final LoanCalculator loanCalculator;
    private final LoanScheduleRegistry loanScheduleRegistry;
    private final DomainEventPublisherPort domainEventPublisherPort;
    private final DomainScheduleTaskPort scheduleTask;
    private final LoanPersistencePort loanPersistencePort;

    /**
     * Calculates the loan schedule based on the provided loan request.
     *
     * @param loanRequest {@link LoanRequest} The loan request containing loan details.
     * @return A Mono containing the LoanResponse with calculated parameters and payment details.
     */
    public Mono<LoanResponseAppDto> calculateLoanSchedule(LoanRequestAppDto loanRequest) {

        LoanResultDomain loanResult = loanCalculator.calculate(LoanMapper.mapLoanRequestToLoanCommand(loanRequest));

        LoanScheduleCommand scheduleCommand = LoanScheduleCommand.of(loanResult);
        List<LoanScheduleResultDomain> loanScheduleResult = loanScheduleRegistry.get(loanRequest.scheduleType())
                                                                                .generatePaymentSchedule(scheduleCommand);
        return loanPersistencePort.saveLoan(LoanPersistenceCommand.of(loanResult, loanScheduleResult))
                                  .onErrorResume(LoanScheduleAppServiceImpl::onSaveErrorThrowIllegalState)
                                  .flatMap(persistenceResult -> {
                                      LoanResponseAppDto response =
                                              LoanMapper.mapLoanPersistenceResultToLoanResponse(persistenceResult);
                                      Mono<Void> publishEvent = getPublishEvent(response, domainEventPublisherPort);
                                      Mono<Void> scheduleNotification = getScheduleNotification(response, scheduleTask);
                                      return publishEvent
                                              .onErrorResume(e -> onPublishEventIgnoreError(e, response))
                                              .then(scheduleNotification)
                                              .onErrorResume(e -> onPublishScheduledNotificationIgnoreError(e, response))
                                              .thenReturn(response);
                                  });
    }

    private static Mono<Void> onPublishScheduledNotificationIgnoreError(Throwable e, LoanResponseAppDto response) {
        return onPublishError(
                "Failed to schedule notification for loan: {}",
                response,
                e);
    }

    private static Mono<Void> onPublishEventIgnoreError(Throwable e, LoanResponseAppDto response) {
        return onPublishError("Failed to publish event for loan: {}",
                              response,
                              e);
    }

    private static Mono<Void> onPublishError(String s, LoanResponseAppDto response, Throwable e) {
        log.error(s,
                  response.loanId(),
                  e.getMessage());
        return Mono.empty();
    }

    private static Mono<LoanPersistenceResult> onSaveErrorThrowIllegalState(Throwable e) {
        log.error("Failed to save loan", e);
        return Mono.error(new IllegalStateException(e));
    }

    private static Mono<Void> getScheduleNotification(LoanResponseAppDto response, DomainScheduleTaskPort scheduleTask) {
        return Mono.fromRunnable(() ->
                                         scheduleTask.schedule(new NotifyDomainScheduledTask(
                                                 response.loanId(),
                                                 1L))
        );
    }

    private static Mono<Void> getPublishEvent(LoanResponseAppDto response,
                                              DomainEventPublisherPort domainEventPublisherPort) {
        return Mono.fromRunnable(() ->
                                         domainEventPublisherPort.publishLoanScheduleEvent(
                                                 new CalculatedDomainEvent(response.loanId()))
        );
    }
}
