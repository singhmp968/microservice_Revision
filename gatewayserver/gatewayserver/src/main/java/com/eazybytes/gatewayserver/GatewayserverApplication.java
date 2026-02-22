package com.eazybytes.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

    @Bean
    public RouteLocator eazyBytesRouterLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route( r -> r
                        .path("/eazybytes/accounts/**")
                        .filters(f->f.rewritePath("/eazybytes/accounts/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .circuitBreaker(c -> c.setName("accountsCircuitBreaker")
                                        .setFallbackUri("forward:/accountsFallback"))

                        )
                        .uri("lb://ACCOUNTS"))

                .route( r -> r
                        .path("/eazybytes/loans/**")
                        .filters(f->f.rewritePath("/eazybytes/loans/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                                .retry(c -> c.setRetries(3).setMethods(HttpMethod.GET)
                                        .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2,true)

                                )

                        )
                        .uri("lb://LOANS"))

                .build();
    }

}
