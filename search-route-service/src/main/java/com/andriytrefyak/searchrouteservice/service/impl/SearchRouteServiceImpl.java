package com.andriytrefyak.searchrouteservice.service.impl;

import com.andriytrefyak.searchrouteservice.finder.RouteFinder;
import com.andriytrefyak.searchrouteservice.dto.RouteDto;
import com.andriytrefyak.searchrouteservice.service.SearchRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchRouteServiceImpl implements SearchRouteService {

    @Autowired
    private RouteFinder routeFinder;

    @Override
    public List<RouteDto> calculateShortestWay(final String departureCityName, final String arrivalCityName, final Boolean direct) {
        if (direct) {
            return Collections.singletonList(routeFinder.findShortestDirectRoute(departureCityName, arrivalCityName));
        }
        return routeFinder.findShortestRouteIncludingConnections(departureCityName, arrivalCityName);
    }

}
