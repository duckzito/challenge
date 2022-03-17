package com.challenge.repositories;

import com.challenge.domain.BTCPrice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface BTCPriceRepository extends ReactiveSortingRepository<BTCPrice, String> {
    Flux<BTCPrice> findByCreatedAtBetween(LocalDateTime first, LocalDateTime second, Pageable pageable);
    Flux<BTCPrice> findByCreatedAtBetween(LocalDateTime first, LocalDateTime second);

    @Query("{ id: { $exists: true }}")
    Flux<BTCPrice> findAll(Pageable pageable);
}
