package com.timvero.loanschedule.configuration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

@TestConfiguration
@Profile("test")
public class TestClockConfig {

    @Bean
    public Clock clock() {
        return Clock.fixed(
                LocalDate.of(2025, 4, 2).atStartOfDay(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault()
        );
    }
}
