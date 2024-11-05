package com.product.inventory.inventory.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the OpenAPI documentation.
 * This configuration is responsible for creating and customizing the OpenAPI object.
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
public class OpenAPIConfig {

    /**
     * Creates and returns a custom OpenAPI object.
     * This object holds the information for the OpenAPI documentation, including the title,
     * version, description, terms of service, and license of the API.
     *
     * @return the customized OpenAPI object
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Test and exercise for knowmadmood")
                        .version("0.1")
                        .description("In the company's e-commerce database we have the PRICES table that reflects the final price (pvp) and the rate that applies to a product in a chain between certain dates")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
