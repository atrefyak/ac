package com.andriytrefyak.searchrouteservice.service;

import com.andriytrefyak.searchrouteservice.dto.RouteDto;

import java.util.List;

public interface SearchRouteService {

    public List<RouteDto> calculateShortestWay(final String departureCityName, final String arrivalCityName, Boolean direct);

}
