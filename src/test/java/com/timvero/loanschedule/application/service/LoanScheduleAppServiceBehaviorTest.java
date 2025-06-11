package com.timvero.loanschedule.application.service;

import com.timvero.loanschedule.application.dto.LoanRequestAppDto;
import com.timvero.loanschedule.application.dto.LoanResponseAppDto;
import com.timvero.loanschedule.application.registry.LoanScheduleRegistry;
import com.timvero.loanschedule.domain.loan.port.in.LoanCalculator;
import com.timvero.loanschedule.domain.loan.port.in.LoanResultDomain;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResultDomain;
import com.timvero.loanschedule.domain.schedule.port.in.ScheduleGenerator;
import com.timvero.loanschedule.domain.shared.port.out.event.DomainEventPublisherPort;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistencePort;
import com.timvero.loanschedule.domain.shared.port.out.persistance.LoanPersistenceResult;
import com.timvero.loanschedule.domain.shared.port.out.schedule.DomainScheduleTaskPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.timvero.loanschedule.fixture.LoanParametersData.buildLoanResults;
import static com.timvero.loanschedule.fixture.LoanPersistenceResultData.buildAnnualLoanPersistenceResult;
import static com.timvero.loanschedule.fixture.LoanRequestData.buildDefaultAnnuityLoanRequestApp;
import static com.timvero.loanschedule.fixture.LoanResponseData.buildSampleLoanResponseAppDto;
import static com.timvero.loanschedule.fixture.LoanResponseData.buildSampleLoanScheduleResults;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanScheduleAppServiceBehaviorTest {

    @Mock
    private LoanScheduleRegistry loanScheduleRegistry;

    @Mock
    private LoanCalculator loanCalculator;

    @Mock
    private ScheduleGenerator monthlyScheduleGenerator;

    @Mock
    private DomainEventPublisherPort domainEventPublisherPort;

    @Mock
    private DomainScheduleTaskPort scheduleTask;

    @Mock
    private LoanPersistencePort loanPersistencePort;

    private LoanScheduleAppService loanScheduleAppService;

    @BeforeEach
    void setUp() {
        loanScheduleAppService = new LoanScheduleAppServiceImpl(
                loanCalculator,
                loanScheduleRegistry,
                domainEventPublisherPort,
                scheduleTask,
                loanPersistencePort
        );
    }

    @Test
    void givenAnnuityLoanRequestData_whenCalculateLoanSchedule_thenReturnAnnuityLoanCalculatedData() {
        // given
        LoanRequestAppDto givenRequest = buildDefaultAnnuityLoanRequestApp().build();
        List<LoanScheduleResultDomain> givenDomainResponse = buildSampleLoanScheduleResults();
        LoanResultDomain givenLoanResult = buildLoanResults().build();
        LoanPersistenceResult givenLoanPersistenceResult = buildAnnualLoanPersistenceResult().build();
        when(loanScheduleRegistry.get(givenRequest.scheduleType())).thenReturn(monthlyScheduleGenerator);
        when(monthlyScheduleGenerator.generatePaymentSchedule(any())).thenReturn(givenDomainResponse);
        when(loanCalculator.calculate(any())).thenReturn(givenLoanResult);
        when(loanPersistencePort.saveLoan(any())).thenReturn(Mono.just(givenLoanPersistenceResult));

        LoanResponseAppDto expectedResponse = buildSampleLoanResponseAppDto().build();

        // when
        Mono<LoanResponseAppDto> result = loanScheduleAppService.calculateLoanSchedule(givenRequest);

        // then
        StepVerifier.create(result)
                    .consumeNextWith(actual ->
                                             assertThat(actual).usingRecursiveComparison().isEqualTo(expectedResponse)
                    )
                    .verifyComplete();
    }

}
