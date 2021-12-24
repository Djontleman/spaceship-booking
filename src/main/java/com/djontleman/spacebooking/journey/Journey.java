package com.djontleman.spacebooking.journey;

import com.djontleman.spacebooking.flight.Flight;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Journey {

    private Long id;
    private String origin;
    private String destination;
    @JsonIgnoreProperties("flight")
    private List<Flight> flightList;

}
