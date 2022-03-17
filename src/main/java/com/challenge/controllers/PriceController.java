package com.challenge.controllers;

import com.challenge.domain.BTCPrice;
import com.challenge.services.BTCPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/challenge", produces = "application/json")
@RequiredArgsConstructor
public class PriceController {
    private final BTCPriceService service;

    @GetMapping("/price/average")
    public Mono<Double> average(@RequestParam("first") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime firstDate,
                                @RequestParam("second") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime secondDate) {
        return this.service.getAverage(firstDate, secondDate)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/prices")
    public Flux<BTCPrice> get(@RequestParam(value = "first", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> firstDate,
                              @RequestParam(value = "second", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> secondDate,
                              @RequestParam("page") int page) {
        return this.service.getPrices(firstDate, secondDate, page)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

    @GetMapping("/price")
    public Mono<BTCPrice> get(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> date) {
        return this.service.getPrices(date, Optional.empty(), 0)
                .next()
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
