package com.timvero.loanschedule.infra.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Operators;

@Configuration
@Order(-1)
public class MdcReactConfig {
    private static final String REQUEST_ID = "request-id";
    private static final String MDC_KEY = "request-id";

    @PostConstruct
    public void configureReactorHooks() {
        Hooks.onEachOperator("mdc", Operators.lift((scannable, subscriber) ->
                                                           new MdcContextLifter<>(subscriber, MDC_KEY, REQUEST_ID)));
    }

    @PreDestroy
    public void cleanupHooks() {
        Hooks.resetOnEachOperator("mdc");
    }

}
