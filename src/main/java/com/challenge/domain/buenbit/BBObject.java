package com.challenge.domain.buenbit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class )
public class BBObject {
    @JsonProperty("btcars")
    public BBItem btcars;
}
