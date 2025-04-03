package com.timvero.loanschedule.controller;

import com.timvero.loanschedule.configuration.TestClockConfig;
import com.timvero.loanschedule.dto.LoanRequest;
import com.timvero.loanschedule.dto.LoanResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.timvero.loanschedule.fixture.LoanRequestData.buildLoanRequestValidSimple;
import static com.timvero.loanschedule.fixture.LoanResponseData.buildSampleLoanResponse;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestClockConfig.class)
@ActiveProfiles("test")
class LoanScheduleControllerIntegrationTest {

    private static final String API_ENDPOINT = "/api/v1/loan/schedule";

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldReturnValidLoanResponse_whenGetAnnuityLoanSchedule_givenValidLoanRequest() {
        // given
        LoanRequest givenRequest = buildLoanRequestValidSimple().build();
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
