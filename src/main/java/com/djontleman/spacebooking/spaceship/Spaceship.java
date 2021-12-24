package com.djontleman.spacebooking.spaceship;

import com.djontleman.spacebooking.flight.Flight;
import com.djontleman.spacebooking.genericspaceship.GenericSpaceship;
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
    private Long genericSpaceshipId;
    @JsonIgnoreProperties({"id", "spaceshipsList"})
    private GenericSpaceship genericSpaceship;
    @JsonIgnoreProperties({"spaceshipId", "spaceship"})
    private List<Flight> flightList;
}
