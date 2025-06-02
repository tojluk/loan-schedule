package com.timvero.loanschedule.infra.adaptor.in.controller;

import com.timvero.loanschedule.infra.dto.LoanRequest;
import com.timvero.loanschedule.infra.dto.LoanResponse;
import com.timvero.loanschedule.application.service.LoanScheduleAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * LoanScheduleController is a REST controller that handles loan schedule-related operations.
 * It provides an endpoint for calculating the loan schedule based on the provided request.
 */
@RestController
@RequestMapping("/api/v1/loan")
@RequiredArgsConstructor
@Tag(name = "Loans", description = "API controller for Loans Schedule")
public class LoanScheduleController {

    private final LoanScheduleAppService loanScheduleAppService;

    @PostMapping(value = "/schedule", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Calculate loan schedule")
    public Mono<LoanResponse> calculateLoanSchedule(@RequestBody @Valid LoanRequest loanRequest) {
        return loanScheduleAppService.calculateLoanSchedule(loanRequest);
    }

}
