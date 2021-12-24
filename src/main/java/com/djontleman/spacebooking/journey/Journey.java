package com.djontleman.spacebooking.journey;

import lombok.*;

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

}
