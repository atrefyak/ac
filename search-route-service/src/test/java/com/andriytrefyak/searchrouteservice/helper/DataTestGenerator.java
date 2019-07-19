package com.andriytrefyak.searchrouteservice.helper;

import com.andriytrefyak.searchrouteservice.dto.CityDto;
import com.andriytrefyak.searchrouteservice.dto.RouteDto;

import java.time.LocalTime;

public class DataTestGenerator {

    private static final Long ID = 1L;

    public static CityDto generateCity(final Long ID, final String name) {
        final CityDto city = new CityDto();
        city.setId(ID);
        city.setName(name);
        return city;
    }


    public static RouteDto generateRoute(final Long departureCidyId, final Long arrivalCityId, final LocalTime arrivalTime) {
        final RouteDto route = new RouteDto();
        route.setDepartureCity(departureCidyId);
        route.setArrivalCity(arrivalCityId);
        route.setArrivalTime(arrivalTime);
        route.setDepartureTime(LocalTime.now());
        return route;
    }
}
