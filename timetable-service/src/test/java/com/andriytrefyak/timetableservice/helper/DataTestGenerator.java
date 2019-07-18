package com.andriytrefyak.timetableservice.helper;

import com.andriytrefyak.timetableservice.domain.City;
import com.andriytrefyak.timetableservice.domain.Route;

import java.time.LocalTime;

public class DataTestGenerator {

    private static final Long ID = 1L;
    private static final String NAME = "name";

    public static City generateCity(final Long ID, final String name) {
        final City city = new City();
        city.setId(ID);
        city.setName(NAME);
        return city;
    }


    public static Route generateRoute(final City departure, final City arrival) {
        final Route route = new Route();
        City cityKyiv = DataTestGenerator.generateCity(1L, "Kyiv");
        City cityLviv = DataTestGenerator.generateCity(2L, "Lviv");
        route.setDepartureCity(cityLviv);
        route.setArrivalCity(cityKyiv);
        route.setArrivalTime(LocalTime.now());
        route.setDepartureTime(LocalTime.now());
        return route;
    }
}
