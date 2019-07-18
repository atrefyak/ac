package com.andriytrefyak.searchrouteservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
public class CityDto implements Serializable {
    private Long id;
    private String name;
}
