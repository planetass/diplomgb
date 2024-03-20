package ru.gb.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GateWayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GateWayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("UserService",r->r.path("/**")
                        .uri("http://localhost:8081/"))
                .route("offerService",r->r.path("/**")
                        .uri("http://localhost:8082/")).build();}

}
