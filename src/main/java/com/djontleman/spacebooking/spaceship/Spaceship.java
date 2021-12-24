package com.djontleman.spacebooking.spaceship;

import com.djontleman.spacebooking.flight.Flight;
import com.djontleman.spacebooking.genericspaceship.GenericSpaceship;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Spaceship {
    private Long id;
    private String callSign;
    @JsonIgnore
    private Long genericSpaceshipId;
    @JsonIgnoreProperties({"spaceshipsList"})
    private GenericSpaceship genericSpaceship;
    @JsonIgnoreProperties({"spaceship"})
    private List<Flight> flightList;
}
