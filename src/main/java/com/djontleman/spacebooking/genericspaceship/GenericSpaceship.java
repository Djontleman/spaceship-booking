package com.djontleman.spacebooking.genericspaceship;

import lombok.*;

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

}
