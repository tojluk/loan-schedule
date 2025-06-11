package com.timvero.loanschedule.infra.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.r2dbc")
public class R2dbcProperties {
    private String url = "r2dbc:h2:mem:///testdb";
    private String username = "sa";
    private String password = "password";
}
