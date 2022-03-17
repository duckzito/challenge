package com.challenge.services;

import com.challenge.domain.BTCPrice;
import com.challenge.domain.rules.Rule;
import com.challenge.repositories.BTCPriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class RuleService {
    private final static int PAGE_SIZE = 10;
    private final BTCPriceRepository repository;


    public Flux<Rule<Flux<BTCPrice>>> getAllRules(Optional<LocalDateTime> first, Optional<LocalDateTime> second, int page) {
        return Flux.just(createEmptyParamtersRule(first, second, page),
                createUniqueResultRule(first,second, page),
                createAllParamtersRule(first, second, page));
    }

    private Rule<Flux<BTCPrice>> createUniqueResultRule(Optional<LocalDateTime> first, Optional<LocalDateTime> second, int page) {
        Pageable pageRequest = PageRequest.of(page, 1);
        return createRule(
                ()-> first.isPresent() && second.isEmpty(),
                ()-> repository.findByCreatedAtBetween(first.get(), first.get().plusMinutes(10),pageRequest)
        );
    }

   private Rule<Flux<BTCPrice>> createEmptyParamtersRule(Optional<LocalDateTime> first, Optional<LocalDateTime> second, int page) {
        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));
        return createRule(
                ()-> first.isEmpty() && second.isEmpty(),
                ()-> repository.findAll(pageRequest)
        );
    }

    private Rule<Flux<BTCPrice>> createAllParamtersRule(Optional<LocalDateTime> first, Optional<LocalDateTime> second, int page) {
        Pageable pageRequest = PageRequest.of(page, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "createdAt"));
        return createRule(
                ()-> first.isPresent() && second.isPresent(),
                ()-> repository.findByCreatedAtBetween(first.get(), second.get(), pageRequest)
        );
    }


    private static Rule<Flux<BTCPrice>> createRule(Supplier<Boolean> condition, Supplier<Flux<BTCPrice>> process) {
        return new Rule<>(condition,process);
    }

}
