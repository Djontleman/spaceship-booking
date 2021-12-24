package com.djontleman.spacebooking.flight;

import com.djontleman.spacebooking.journey.Journey;
import com.djontleman.spacebooking.spaceship.Spaceship;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long journeyId;
    @JsonIgnoreProperties({"flightList"})
    private Journey journey;
    @JsonIgnore
    private Long spaceshipId;
    @JsonIgnoreProperties({"flightList"})
    private Spaceship spaceship;

}
