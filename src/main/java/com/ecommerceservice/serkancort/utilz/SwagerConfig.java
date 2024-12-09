package com.ecommerceservice.serkancort.utilz;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwagerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .packagesToScan("com.ecommerceservice.serkancort.controller")
                .pathsToMatch("/customer/**")
                .build();
    }
}
