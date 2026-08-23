package com.ratnesh.connecthub.apigateway.filter;

import com.ratnesh.connecthub.apigateway.config.SecurityProperties;
import com.ratnesh.connecthub.apigateway.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class GatewayJwtAuthFilter implements GlobalFilter, Ordered {

    private final SecurityProperties securityProperties;
    private final JwtService jwtService;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return chain.filter(exchange);
        }
        String path = request.getURI().getPath();

        boolean isPublic = securityProperties.getPublicRoutes()
                .stream()
                .anyMatch(route -> antPathMatcher.match(route, path));

        if (isPublic) {
            return chain.filter(exchange);
        }

        String header = request.getHeaders()
                .getFirst("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {

            log.error(
                    "Missing or invalid Authorization header for path: {}",
                    path
            );

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String token = header.substring(7);

        try {

            jwtService.validateToken(token);

            log.info("JWT validated for path: {}", path);

        } catch (Exception e) {

            log.error(
                    "JWT validation failed: {}",
                    e.getMessage()
            );

            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);
//            It completes the reactive response and stops further processing of the request.
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}