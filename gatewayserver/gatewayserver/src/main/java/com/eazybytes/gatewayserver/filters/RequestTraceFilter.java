package com.eazybytes.gatewayserver.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;



// responsoble for generating corellation id for each request and adding it to the header of the request and response
@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestTraceFilter.class);
    @Autowired
    FilterUtility filterUtility;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(requestHeaders)) {
            logger.info("correlation id found in the request header: {}. ", filterUtility.getCorrelationId(requestHeaders));
        } else {
            String correlationId = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationId);
            logger.info("correlation id generated and set to the request header: {}. ", correlationId);
        }
        return chain.filter(exchange);
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
