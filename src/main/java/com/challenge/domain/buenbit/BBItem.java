package com.challenge.domain.buenbit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class )
public class BBItem {
    String currency;
    @JsonProperty("bid_currency")
    String bidCurrency;
    @JsonProperty("ask_currency")
    String askCurrency;
    @JsonProperty("purchase_price")
    String purchasePrice;
    @JsonProperty("selling_price")
    String sellingPrice;
    @JsonProperty("market_identifier")
    String marketIdentifier;
}
