package com.example.gatewayservice;
import com.example.gatewayservice.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class GatewayConfig {

    @Autowired
    private AuthFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:9091"))
                //.route("service-a", r -> r.path("/api/**").filters(f -> f.filter(filter)).uri("http://localhost:9099"))
                .route("account-service", r -> r.path("/api/account/**").filters(f -> f.filter(filter)).uri("http://localhost:9092"))
                .route("location-service", r -> r.path("/api/location/**").filters(f -> f.filter(filter)).uri("http://localhost:9093"))
                .build();
    }

}
