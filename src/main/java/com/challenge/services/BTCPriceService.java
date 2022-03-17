package com.challenge.services;

import com.challenge.clients.BuenBitClient;
import com.challenge.domain.BTCPrice;
import com.challenge.domain.Currency;
import com.challenge.domain.buenbit.BBObject;
import com.challenge.domain.buenbit.BuenBitResponse;
import com.challenge.repositories.BTCPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.math.MathFlux;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;


@Slf4j
@Service
@RequiredArgsConstructor
public class BTCPriceService {
    private final BTCPriceRepository repository;
    private final BuenBitClient client;
    private final RuleService ruleService;

    public Mono<Double> getAverage(LocalDateTime first, LocalDateTime second) {
        Flux<Double> prices = repository.findByCreatedAtBetween(first, second)
                .map(BTCPrice::getPurchasePrice)
                .map(BigDecimal::doubleValue);

        return MathFlux.averageDouble(prices);
    }

    public Flux<BTCPrice> getPrices(Optional<LocalDateTime> first, Optional<LocalDateTime> second, int page) {
        return ruleService
                .getAllRules(first, second, page)
                .filter(r -> r.condition.get())
                .take(1)
                .map(r -> r.process)
                .flatMap(Supplier::get);
    }

    @Scheduled(fixedDelay = 10000)
    private void getPrices() {
        this.client.getData()
                .map(BuenBitResponse::getObject)
                .map(BBObject::getBtcars)
                .map(data -> BTCPrice.builder()
                        .createdAt(LocalDateTime.now())
                        .askCurrency(Currency.valueOf(data.getAskCurrency().toUpperCase()))
                        .bidCurrency(Currency.valueOf(data.getBidCurrency().toUpperCase()))
                        .purchasePrice(new BigDecimal(data.getPurchasePrice()))
                        .sellingPrice(new BigDecimal(data.getSellingPrice()))
                        .currency(Currency.valueOf(data.getCurrency().toUpperCase()))
                        .marketIdentifier(data.getMarketIdentifier())
                        .build())
                .flatMap(this.repository::save)
                .subscribe(price -> log.info("{} BTC Price {} Saved", price.getCreatedAt(), price.getPurchasePrice()));
    }


}
