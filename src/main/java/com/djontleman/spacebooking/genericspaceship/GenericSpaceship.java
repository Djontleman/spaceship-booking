package com.djontleman.spacebooking.genericspaceship;

import com.djontleman.spacebooking.spaceship.Spaceship;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class GenericSpaceship {

    private Long id;
    private String brand;
    private String model;
    private Integer capacity;
    @JsonIgnoreProperties({"genericSpaceshipId", "genericSpaceship"})
    private List<Spaceship> spaceshipsList;

}
