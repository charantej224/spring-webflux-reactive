package com.charan.event.reactiveservice.config;

import com.charan.event.reactiveservice.controller.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ApplicationConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(CustomerHandler customerHandler) {
        return RouterFunctions.route(GET("/customers").and(accept(MediaType.APPLICATION_JSON)), customerHandler::getAllCustomers)
                .andRoute(POST("/customers").and(accept(MediaType.APPLICATION_JSON)), customerHandler::saveCustomer)
                .andRoute(PUT("/customers/{id}").and(accept(MediaType.APPLICATION_JSON)), customerHandler::updateCustomer)
                .andRoute(DELETE("/customers/{id}").and(accept(MediaType.APPLICATION_JSON)), customerHandler::deleteCustomer)
                .andRoute(GET("/customers/{id}").and(accept(MediaType.APPLICATION_JSON)), customerHandler::getCustomer);
    }

}
