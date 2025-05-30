package com.timvero.loanschedule.service;

import com.timvero.loanschedule.application.registry.LoanScheduleRegistry;
import com.timvero.loanschedule.application.service.LoanScheduleAppService;
import com.timvero.loanschedule.domain.loan.port.in.LoanCalculator;
import com.timvero.loanschedule.domain.loan.port.in.LoanResult;
import com.timvero.loanschedule.domain.schedule.port.in.LoanScheduleResult;
import com.timvero.loanschedule.domain.schedule.port.in.ScheduleGenerator;
import com.timvero.loanschedule.infra.dto.LoanRequest;
import com.timvero.loanschedule.infra.dto.LoanResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static com.timvero.loanschedule.fixture.LoanParametersData.buildLoanResults;
import static com.timvero.loanschedule.fixture.LoanRequestData.buildLoanRequestValidSimple;
import static com.timvero.loanschedule.fixture.LoanResponseData.buildSampleLoanResponse;
import static com.timvero.loanschedule.fixture.LoanResponseData.buildSampleLoanScheduleResults;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanScheduleAppServiceUnitTest {

    @Mock
    private LoanScheduleRegistry loanScheduleRegistry;

    @Mock
    private LoanCalculator loanCalculator;


    @Mock
    private ScheduleGenerator monthlyScheduleGenerator;

    @InjectMocks
    private LoanScheduleAppService loanScheduleAppService;

    @Test
    void shouldReturnLoanResponse_whenCalculateLoanSchedule_givenValidLoanRequest() {
        // given
        LoanRequest givenRequest = buildLoanRequestValidSimple().build();
        List<LoanScheduleResult> givenDomainResponse = buildSampleLoanScheduleResults();
        LoanResult givenLoanResult = buildLoanResults().build();
        when(loanScheduleRegistry.get(givenRequest.scheduleType())).thenReturn(monthlyScheduleGenerator);
        when(monthlyScheduleGenerator.generatePaymentSchedule(any())).thenReturn(givenDomainResponse);
        when(loanCalculator.calculate(any())).thenReturn(givenLoanResult);

        LoanResponse expectedResponse = buildSampleLoanResponse().build();
        // when
        Mono<LoanResponse> result = loanScheduleAppService.calculateLoanSchedule(givenRequest);

        // then
        StepVerifier.create(result)
                    .consumeNextWith(actual ->
                                             assertThat(actual).usingRecursiveComparison().isEqualTo(expectedResponse)
                    )
                    .verifyComplete();
    }
}
