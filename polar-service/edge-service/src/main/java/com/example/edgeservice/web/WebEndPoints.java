package com.example.edgeservice.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;


@Configuration
public class WebEndPoints {

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions.route()
                .GET(
                        "/service-fallback",
                        request -> ServerResponse.ok().body(Mono.just(""), String.class)
                )

                //서비스에 장애가 있다는 것을 고지
                .POST(
                        "/service-fallback",
                        request -> ServerResponse.status(SERVICE_UNAVAILABLE).build()
                )
                .PUT(
                        "/service-fallback",
                        request -> ServerResponse.ok().body(Mono.just(""), String.class)
                )
                .DELETE(
                        "/service-fallback",
                        request -> ServerResponse.status(SERVICE_UNAVAILABLE).build()
                )
                .build();
    }
}
