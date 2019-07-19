package com.andriytrefyak.searchrouteservice.finder.impl;

import com.andriytrefyak.searchrouteservice.dto.CityDto;
import com.andriytrefyak.searchrouteservice.dto.RouteDto;
import com.andriytrefyak.searchrouteservice.exception.RouteNotFoundException;
import com.andriytrefyak.searchrouteservice.integration.CityServiceIntegration;
import com.andriytrefyak.searchrouteservice.integration.RouteServiceIntegration;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.andriytrefyak.searchrouteservice.helper.DataTestGenerator.generateCity;
import static com.andriytrefyak.searchrouteservice.helper.DataTestGenerator.generateRoute;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RouteFinderImplTest {

    private static final String DEPARTURE_CITY_NAME = "Lviv";
    private static final String ARRIVAL_CITY_NAME = "Kyiv";
    private static final Long DEPARTURE_CITY_ID = 1L;
    private static final Long ARRIVAL_CITY_ID = 2L;
    private static final Long ANOTHER_ARRIVAL_CITY_ID = 3L;
    private static final String ANOTHER_ARRIVAL_CITY_NAME = "Zhytomyr";

    @Mock
    private CityServiceIntegration cityServiceIntegration;

    @Mock
    private RouteServiceIntegration routeServiceIntegration;

    @InjectMocks
    private RouteFinderImpl routeFinder;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void shouldFindDirectRoute() {
        final List<RouteDto> routeDtos = Arrays.asList(generateRoute(DEPARTURE_CITY_ID, ARRIVAL_CITY_ID,LocalTime.now().plusHours(5)), generateRoute(DEPARTURE_CITY_ID, ANOTHER_ARRIVAL_CITY_ID, LocalTime.now().plusHours(2)));
        given(cityServiceIntegration.getCityByName(ARRIVAL_CITY_NAME)).willReturn(generateCity(ARRIVAL_CITY_ID, ARRIVAL_CITY_NAME));
        given(routeServiceIntegration.getRoutesByDepartureCityName(DEPARTURE_CITY_NAME))
                .willReturn(routeDtos);

        final RouteDto result = routeFinder.findShortestDirectRoute(DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME);

        assertEquals(routeDtos.get(0), result);
    }

    @Test
    public void shouldThrowRouteNotFoundExceptionWhenNoDirectRoute() {
        final List<RouteDto> routeDtos = Collections.singletonList(generateRoute(DEPARTURE_CITY_ID, ANOTHER_ARRIVAL_CITY_ID, LocalTime.now().plusHours(4)));
        given(cityServiceIntegration.getCityByName(ARRIVAL_CITY_NAME)).willReturn(generateCity(ARRIVAL_CITY_ID, ARRIVAL_CITY_NAME));
        given(routeServiceIntegration.getRoutesByDepartureCityName(DEPARTURE_CITY_NAME))
                .willReturn(routeDtos);

        expectedException.expect(RouteNotFoundException.class);
        expectedException.expectMessage(String.format("There is no direct route from %s to %s", DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME));

        routeFinder.findShortestDirectRoute(DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME);
    }

    @Test
    public void shouldFindDirectRouteIncludingConnections() {
        final List<CityDto> cities = Arrays.asList(generateCity(ARRIVAL_CITY_ID, ARRIVAL_CITY_NAME), generateCity(DEPARTURE_CITY_ID, DEPARTURE_CITY_NAME));
        final List<RouteDto> routeDtos = Arrays.asList(generateRoute(DEPARTURE_CITY_ID, ARRIVAL_CITY_ID, LocalTime.now().plusHours(3))
                , generateRoute(DEPARTURE_CITY_ID, ARRIVAL_CITY_ID, LocalTime.now().plusHours(2)));

        given(cityServiceIntegration.getCityByName(DEPARTURE_CITY_NAME)).willReturn(generateCity(DEPARTURE_CITY_ID, DEPARTURE_CITY_NAME));
        given(cityServiceIntegration.getCityByName(ARRIVAL_CITY_NAME)).willReturn(generateCity(ARRIVAL_CITY_ID, ARRIVAL_CITY_NAME));

        given(cityServiceIntegration.getAllCities()).willReturn(cities);
        given(routeServiceIntegration.getAllRoutes()).willReturn(routeDtos);

        final List<RouteDto> result = routeFinder.findShortestRouteIncludingConnections(DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME);
        assertEquals(1, result.size());
        assertEquals(routeDtos.get(1), result.get(0));
    }

    @Test
    public void shouldFindRouteWithStopsIncludingConnections() {
        final List<CityDto> cities = Arrays.asList(generateCity(ARRIVAL_CITY_ID, ARRIVAL_CITY_NAME), generateCity(DEPARTURE_CITY_ID, DEPARTURE_CITY_NAME)
                , generateCity(ANOTHER_ARRIVAL_CITY_ID, ANOTHER_ARRIVAL_CITY_NAME));

        final List<RouteDto> routeDtos = Arrays.asList(generateRoute(DEPARTURE_CITY_ID, ANOTHER_ARRIVAL_CITY_ID,
                LocalTime.now().plusHours(3)),generateRoute(ANOTHER_ARRIVAL_CITY_ID, ARRIVAL_CITY_ID, LocalTime.now().plusHours(3)));

        given(cityServiceIntegration.getCityByName(DEPARTURE_CITY_NAME)).willReturn(generateCity(DEPARTURE_CITY_ID, DEPARTURE_CITY_NAME));
        given(cityServiceIntegration.getCityByName(ARRIVAL_CITY_NAME)).willReturn(generateCity(ARRIVAL_CITY_ID, ARRIVAL_CITY_NAME));

        given(cityServiceIntegration.getAllCities()).willReturn(cities);
        given(routeServiceIntegration.getAllRoutes()).willReturn(routeDtos);

        final List<RouteDto> result = routeFinder.findShortestRouteIncludingConnections(DEPARTURE_CITY_NAME, ARRIVAL_CITY_NAME);

        assertEquals(2, result.size());
        assertEquals(routeDtos.get(0), result.get(0));
        assertEquals(routeDtos.get(1), result.get(1));
    }

    @Test
    public void shouldThrowRouteNotFoundExceptionWhenNoRouteIncludingConnections() {
        final List<CityDto> cities = Arrays.asList(generateCity(ARRIVAL_CITY_ID, ARRIVAL_CITY_NAME), generateCity(DEPARTURE_CITY_ID, DEPARTURE_CITY_NAME),
                generateCity(ANOTHER_ARRIVAL_CITY_ID, ANOTHER_ARRIVAL_CITY_NAME));

        final List<RouteDto> routeDtos = Collections.singletonList(generateRoute(DEPARTURE_CITY_ID, ARRIVAL_CITY_ID, LocalTime.now().plusHours(1)));

        given(cityServiceIntegration.getCityByName(DEPARTURE_CITY_NAME)).willReturn(generateCity(DEPARTURE_CITY_ID, DEPARTURE_CITY_NAME));
        given(cityServiceIntegration.getCityByName(ANOTHER_ARRIVAL_CITY_NAME)).willReturn(generateCity(ANOTHER_ARRIVAL_CITY_ID, ANOTHER_ARRIVAL_CITY_NAME));

        given(cityServiceIntegration.getAllCities()).willReturn(cities);
        given(routeServiceIntegration.getAllRoutes()).willReturn(routeDtos);

        expectedException.expect(RouteNotFoundException.class);
        expectedException.expectMessage(String.format("There is no any route from %s to %s", DEPARTURE_CITY_NAME, ANOTHER_ARRIVAL_CITY_NAME));

        routeFinder.findShortestRouteIncludingConnections(DEPARTURE_CITY_NAME, ANOTHER_ARRIVAL_CITY_NAME);

    }


}