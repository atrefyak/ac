package com.andriytrefyak.searchrouteservice.service.impl;

import com.andriytrefyak.searchrouteservice.dto.RouteDto;
import com.andriytrefyak.searchrouteservice.finder.RouteFinder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SearchRouteServiceImplTest {

    private static final String DEPARTURE_CITY_NAME = "Lviv";
    private static final String ARRIVAL_CITY_NAME = "Kyiv";

    @Mock
    private RouteFinder routeFinder;

    @Mock
    private RouteDto firstRoute;

    @Mock
    private RouteDto secondRoute;

    @InjectMocks
    private SearchRouteServiceImpl searchRouteService;

    @Before
    public void setUp() {
        given(routeFinder.findShortestDirectRoute(DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME)).willReturn(firstRoute);
        given(routeFinder.findShortestRouteIncludingConnections(DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME)).willReturn(Arrays.asList(firstRoute, secondRoute));
    }

    @Test
    public void shouldFindDirectRoute() {
        final List<RouteDto> result = searchRouteService.calculateShortestWay(DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME, true);

        assertEquals(1, result.size());
        assertEquals(firstRoute, result.get(0));
    }

    @Test
    public void shouldFindRouteWithConnections() {
        final List<RouteDto> result = searchRouteService.calculateShortestWay(DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME, false);

        assertEquals(2, result.size());
        assertEquals(firstRoute, result.get(0));
        assertEquals(secondRoute, result.get(1));
    }
}