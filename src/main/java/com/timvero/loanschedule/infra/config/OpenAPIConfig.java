package com.timvero.loanschedule.infra.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPIConfig swagger config
 */
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI bookingSystemAPI() {
        return new OpenAPI()
                .info(new Info()
                              .title("Unit Loans System API")
                              .description("REST API for loans schedule system")
                              .version("1.0")
                              .contact(new Contact()
                                               .name("Mak D")
                                               .email("md@millecenta.com")));
    }
}
