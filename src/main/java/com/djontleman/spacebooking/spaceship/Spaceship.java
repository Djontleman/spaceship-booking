package com.djontleman.spacebooking.spaceship;

import com.djontleman.spacebooking.genericspaceship.GenericSpaceship;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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
    @JsonIgnoreProperties("id")
    private GenericSpaceship genericSpaceship;
}
