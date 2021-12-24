package com.djontleman.spacebooking.flight;

import com.djontleman.spacebooking.journey.Journey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Flight {

    private Long id;
    private Long journeyId;
    @JsonIgnoreProperties({"id", "flightList"})
    private Journey journey;
    private Long spaceshipId;

}
