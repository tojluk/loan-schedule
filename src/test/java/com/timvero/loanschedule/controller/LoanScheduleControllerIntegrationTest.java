package com.timvero.loanschedule.controller;

import com.timvero.loanschedule.configuration.TestClockConfig;
import com.timvero.loanschedule.domain.shared.port.out.schedule.DomainScheduleTaskPort;
import com.timvero.loanschedule.infra.dto.LoanRequest;
import com.timvero.loanschedule.infra.dto.LoanResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.timvero.loanschedule.fixture.LoanRequestData.buildDefaultAnnuityLoanRequest;
import static com.timvero.loanschedule.fixture.LoanResponseData.buildSampleLoanResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestClockConfig.class)
@ActiveProfiles("test")
class LoanScheduleControllerIntegrationTest {

    private static final String API_ENDPOINT = "/api/v1/loan/schedule";

    @Autowired
    private WebTestClient webTestClient;

    @MockitoSpyBean
    private DomainScheduleTaskPort scheduleTask;

    @BeforeEach
    void setup() {
        // Configure mock to do nothing when schedule is called
        doNothing().when(scheduleTask).schedule(any());
    }

    @Test
    void shouldReturnLoanResponse_whenSchedule_givenValidAnnuityLoanAndMonthlyPayments() {
        // given
        LoanRequest givenRequest = buildDefaultAnnuityLoanRequest().build();
        LoanResponse expected = buildSampleLoanResponse().build();

        // when
        LoanResponse actual = webTestClient.post()
                                           .uri(API_ENDPOINT)
                                           .contentType(MediaType.APPLICATION_JSON)
                                           .body(Mono.just(givenRequest), LoanRequest.class)
                                           .exchange()
                                           .expectStatus().isOk()
                                           .expectBody(LoanResponse.class)
                                           .returnResult()
                                           .getResponseBody();

        // then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);

    }

}
