package com.andriytrefyak.searchrouteservice.calculate;

import com.andriytrefyak.searchrouteservice.dto.RouteDto;

import java.util.List;

public interface RouteFinder {

    List<RouteDto> findShortestRouteIncludingConnections(final String departureCityName, final String arrivalCityName);

    RouteDto findShortestDirectRoute(final String departureCityName, final String arrivalCityName);

}
