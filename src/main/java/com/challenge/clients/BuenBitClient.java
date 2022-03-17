package com.challenge.clients;

import com.challenge.domain.buenbit.BuenBitResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@Component
public class BuenBitClient {
    private final WebClient.Builder builder;

    public Mono<BuenBitResponse> getData() {
        return  builder
                .baseUrl("https://be.buenbit.com/")
                .build()
                .get()
                .uri("/api/market/tickers/")
                .retrieve()
                .bodyToMono(BuenBitResponse.class)
                .doOnError( e -> log.error("error fetching BuenBit data: {}", e.getMessage(), e));
    }

}

