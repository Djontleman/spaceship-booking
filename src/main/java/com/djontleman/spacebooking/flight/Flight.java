package com.djontleman.spacebooking.flight;

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
    private Long spaceshipId;

}
