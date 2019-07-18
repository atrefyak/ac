package com.andriytrefyak.timetableservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class RouteDto implements Serializable {
    private Long id;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private Long departureCity;
    private Long arrivalCity;
}
