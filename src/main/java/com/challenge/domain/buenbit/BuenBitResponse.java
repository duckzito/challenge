package com.challenge.domain.buenbit;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Data
@NoArgsConstructor
@JsonNaming( PropertyNamingStrategies.SnakeCaseStrategy.class )
public class BuenBitResponse {
    @JsonProperty("object")
    BBObject object;

}
