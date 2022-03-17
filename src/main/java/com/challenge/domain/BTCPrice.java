package com.challenge.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "prices")
@Data
@Builder
public class BTCPrice {
    @Id String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime createdAt;
    Currency currency;
    Currency bidCurrency;
    Currency askCurrency;
    BigDecimal purchasePrice;
    BigDecimal sellingPrice;
    String marketIdentifier;

}
