package com.ecommerceservice.serkancort.starter;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@OpenAPIDefinition(
        info = @Info(title = "e-commerce service API", version = "v3", description = "API documentation for e-commerce service")
)
@ComponentScan(basePackages = {"com.ecommerceservice"})
@EntityScan(basePackages = {"com.ecommerceservice"})
@EnableJpaRepositories(basePackages = {"com.ecommerceservice"})
@EnableScheduling
@EnableJpaAuditing
@EnableWebMvc
@SpringBootApplication(scanBasePackages = "com.ecommerceservice")
public class EcommerceServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceServiceApplication.class, args);
    }

}
