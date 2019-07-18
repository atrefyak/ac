package com.andriytrefyak.timetableservice.service.impl;

import com.andriytrefyak.timetableservice.domain.City;
import com.andriytrefyak.timetableservice.domain.Route;
import com.andriytrefyak.timetableservice.dto.RouteDto;
import com.andriytrefyak.timetableservice.exception.RequestedDataNotFoundException;
import com.andriytrefyak.timetableservice.helper.DataTestGenerator;
import com.andriytrefyak.timetableservice.repository.CityRepository;
import com.andriytrefyak.timetableservice.repository.RouteRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceImplTest {

    private static final Long WRONG_ID = 2L;
    private static final String WRONG_NAME = "wrong name";
    private static final String LVIV_NAME = "Lviv";
    private static final String KYIV_NAME = "Kyiv";
    private static final Long LVIV_ID = 1L;
    private static final Long KYIV_ID = 2L;

    private static final City CITY_LVIV = DataTestGenerator.generateCity(LVIV_ID, LVIV_NAME);
    private static final City CITY_KYIV = DataTestGenerator.generateCity(KYIV_ID, KYIV_NAME);

    @Mock
    private RouteRepository routeRepository;

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private RouteServiceImpl routeService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldFindAllRoutes() {
        final Route route = DataTestGenerator.generateRoute(CITY_LVIV, CITY_KYIV);
        given(routeRepository.findAll()).willReturn(Collections.singletonList(route));

        final List<RouteDto> routeDtos = routeService.findAll();

        verifyResults(route, routeDtos);
    }

    @Test
    public void shouldFindByDepartureCityByName() {
        final Route route = DataTestGenerator.generateRoute(CITY_LVIV, CITY_KYIV);
        given(cityRepository.findByName(LVIV_NAME)).willReturn(Optional.of(CITY_LVIV));
        given(routeRepository.findByDepartureCity(CITY_LVIV)).willReturn(Collections.singletonList(route));

        final List<RouteDto> routeDtos = routeService.findByDepartureCityName(LVIV_NAME);

        verifyResults(route, routeDtos);
    }

    @Test
    public void shouldThrowRequestedDataNotFoundExceptionWhenCityNotFoundByName() {
        expectedException.expect(RequestedDataNotFoundException.class);
        expectedException.expectMessage(String.format("City with name %s is not found", WRONG_NAME));

        routeService.findByDepartureCityName(WRONG_NAME);
    }

    @Test
    public void shouldFindByDepartureCityById() {
        final Route route = DataTestGenerator.generateRoute(CITY_KYIV, CITY_LVIV);
        given(cityRepository.findById(KYIV_ID)).willReturn(Optional.of(CITY_KYIV));
        given(routeRepository.findByDepartureCity(CITY_KYIV)).willReturn(Collections.singletonList(route));

        final List<RouteDto> routeDtos = routeService.findByDepartureCityId(KYIV_ID);

        verifyResults(route, routeDtos);
    }

    @Test
    public void shouldThrowRequestedDataNotFoundExceptionWhenCityNotFoundById() {
        expectedException.expect(RequestedDataNotFoundException.class);
        expectedException.expectMessage(String.format("City with id %d is not found", WRONG_ID));

        routeService.findByDepartureCityId(WRONG_ID);
    }

    private void verifyResults(Route route, List<RouteDto> routeDtos) {
        assertEquals(1, routeDtos.size());

        final RouteDto routeDto = routeDtos.get(0);

        assertEquals(route.getArrivalCity().getId(), routeDto.getArrivalCity());
        assertEquals(route.getDepartureCity().getId(), routeDto.getDepartureCity());
        assertEquals(route.getDepartureTime(), routeDto.getDepartureTime());
        assertEquals(route.getArrivalTime(), routeDto.getArrivalTime());
    }

}
