package com.timvero.loanschedule.service;

import com.timvero.loanschedule.dto.LoanParameters;
import com.timvero.loanschedule.dto.LoanRequest;
import com.timvero.loanschedule.dto.LoanResponse;
import com.timvero.loanschedule.service.loan.LoanCalculator;
import com.timvero.loanschedule.service.schedule.ScheduleGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static com.timvero.loanschedule.fixture.LoanParametersData.buildLoanParametersSimple;
import static com.timvero.loanschedule.fixture.LoanRequestData.buildLoanRequestValidSimple;
import static com.timvero.loanschedule.fixture.LoanResponseData.buildSampleLoanResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanScheduleServiceUnitTest {

    @Mock
    private LoanCalculator annuityLoanCalculator;

    @Mock
    private ScheduleGenerator monthlyScheduleGenerator;

    @InjectMocks
    private LoanScheduleService loanScheduleService;

    @Test
    void shouldReturnLoanResponse_whenCalculateLoanSchedule_givenValidLoanRequest() {
        // given
        LoanRequest givenRequest = buildLoanRequestValidSimple().build();
        LoanResponse expectedResponse = buildSampleLoanResponse().build();
        LoanParameters givenLoanParameters = buildLoanParametersSimple().build();
        when(annuityLoanCalculator.calculateLoanParameters(any(LoanRequest.class)))
                .thenReturn(givenLoanParameters);
        when(monthlyScheduleGenerator.generatePaymentSchedule(any(LoanParameters.class)))
                .thenReturn(expectedResponse.paymentDetails());

        // when
        Mono<LoanResponse> result = loanScheduleService.calculateLoanSchedule(givenRequest);

        // then
        StepVerifier.create(result)
                    .consumeNextWith(actual ->
                                             assertThat(actual).usingRecursiveComparison().isEqualTo(expectedResponse)
                    )
                    .verifyComplete();
        ArgumentCaptor<LoanRequest> loanRequestCaptor = ArgumentCaptor.forClass(LoanRequest.class);
        verify(annuityLoanCalculator).calculateLoanParameters(loanRequestCaptor.capture());
        assertThat(loanRequestCaptor.getValue()).usingRecursiveComparison().isEqualTo(givenRequest);
        ArgumentCaptor<LoanParameters> loanParametersCaptor = ArgumentCaptor.forClass(LoanParameters.class);
        verify(monthlyScheduleGenerator).generatePaymentSchedule(loanParametersCaptor.capture());
        assertThat(loanParametersCaptor.getValue()).usingRecursiveComparison().isEqualTo(givenLoanParameters);
    }
}
