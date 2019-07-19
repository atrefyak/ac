package com.andriytrefyak.searchrouteservice.finder.impl;

import com.andriytrefyak.searchrouteservice.dto.CityDto;
import com.andriytrefyak.searchrouteservice.dto.RouteDto;
import com.andriytrefyak.searchrouteservice.exception.RouteNotFoundException;
import com.andriytrefyak.searchrouteservice.finder.RouteFinder;
import com.andriytrefyak.searchrouteservice.integration.CityServiceIntegration;
import com.andriytrefyak.searchrouteservice.integration.RouteServiceIntegration;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RouteFinderImpl implements RouteFinder {

    @Autowired
    private CityServiceIntegration cityServiceIntegration;

    @Autowired
    private RouteServiceIntegration routeServiceIntegration;

    @Override
    public List<RouteDto> findShortestRouteIncludingConnections(final String departureCityName, final String arrivalCityName) {
        final SimpleDirectedWeightedGraph<Long, DefaultWeightedEdge> simpleDirectedWeightedGraph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        prepareVortexData(simpleDirectedWeightedGraph);
        final Map<DefaultWeightedEdge, RouteDto> connectionRoutesMap = buildEdgeMap(simpleDirectedWeightedGraph);
        final List<DefaultWeightedEdge> defaultWeightedEdges = DijkstraShortestPath.findPathBetween(simpleDirectedWeightedGraph, getCityId(departureCityName), getCityId(arrivalCityName));
        if (CollectionUtils.isEmpty(defaultWeightedEdges)) {
            throw new RouteNotFoundException(String.format("There is no any route from %s to %s", departureCityName, arrivalCityName));
        }
        return defaultWeightedEdges.stream().map(connectionRoutesMap::get).collect(Collectors.toList());
    }

    @Override
    public RouteDto findShortestDirectRoute(final String departureCityName, final String arrivalCityName) {
        final CityDto arrivalCity = cityServiceIntegration.getCityByName(arrivalCityName);
        final List<RouteDto> routeDtos = routeServiceIntegration.getRoutesByDepartureCityName(departureCityName);

        return routeDtos.stream().filter(routeDto -> routeDto.getArrivalCity().equals(arrivalCity.getId()))
                .max((o1, o2) -> Long.compare(getTripDuration(o2), getTripDuration(o1))).orElseThrow(()
                        -> new RouteNotFoundException(String.format("There is no direct route from %s to %s", departureCityName, arrivalCityName)));
    }

    private Map<DefaultWeightedEdge, RouteDto> buildEdgeMap(final SimpleDirectedWeightedGraph<Long, DefaultWeightedEdge> simpleDirectedWeightedGraph) {
        final List<RouteDto> routeDtos = routeServiceIntegration.getAllRoutes();
        final Map<DefaultWeightedEdge, RouteDto> map = new HashMap<>();
        routeDtos.forEach(routeDto -> {
            final DefaultWeightedEdge defaultWeightedEdge = simpleDirectedWeightedGraph.getEdge(routeDto.getDepartureCity(), routeDto.getArrivalCity());

            if (defaultWeightedEdge != null) {
                updateWeightIfNeeded(simpleDirectedWeightedGraph, map, routeDto, defaultWeightedEdge);
            } else {
                addNewEdge(simpleDirectedWeightedGraph, map, routeDto);
            }
        });
        return map;
    }

    private void prepareVortexData(final SimpleDirectedWeightedGraph<Long, DefaultWeightedEdge> simpleDirectedWeightedGraph) {
        final List<CityDto> allCities = cityServiceIntegration.getAllCities();
        allCities.forEach(cityDto -> simpleDirectedWeightedGraph.addVertex(cityDto.getId()));
    }

    private void addNewEdge(final SimpleDirectedWeightedGraph<Long, DefaultWeightedEdge> simpleDirectedWeightedGraph,
                            final Map<DefaultWeightedEdge, RouteDto> map, final RouteDto routeDto) {
        final DefaultWeightedEdge defaultWeightedEdge = simpleDirectedWeightedGraph.addEdge(routeDto.getDepartureCity(), routeDto.getArrivalCity());
        simpleDirectedWeightedGraph.setEdgeWeight(defaultWeightedEdge, getTripDuration(routeDto));
        map.put(defaultWeightedEdge, routeDto);
    }

    private void updateWeightIfNeeded(final SimpleDirectedWeightedGraph<Long, DefaultWeightedEdge> simpleDirectedWeightedGraph,
                                      final Map<DefaultWeightedEdge, RouteDto> map, final RouteDto routeDto, final DefaultWeightedEdge defaultWeightedEdge) {
        final double edgeWeight = simpleDirectedWeightedGraph.getEdgeWeight(defaultWeightedEdge);
        final double tripDuration = (double) getTripDuration(routeDto);
        if (edgeWeight > tripDuration) {
            simpleDirectedWeightedGraph.setEdgeWeight(defaultWeightedEdge, tripDuration);
            map.put(defaultWeightedEdge, routeDto);
        }
    }

    private Long getCityId(final String cityName) {
        return cityServiceIntegration.getCityByName(cityName).getId();
    }

    private static long getTripDuration(final RouteDto routeDto) {
        return Duration.between(routeDto.getDepartureTime(), routeDto.getArrivalTime()).getSeconds();
    }
}
