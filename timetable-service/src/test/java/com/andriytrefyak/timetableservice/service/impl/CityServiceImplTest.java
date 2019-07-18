package com.andriytrefyak.timetableservice.service.impl;

import com.andriytrefyak.timetableservice.domain.City;
import com.andriytrefyak.timetableservice.dto.CityDto;
import com.andriytrefyak.timetableservice.exception.RequestedDataNotFoundException;
import com.andriytrefyak.timetableservice.repository.CityRepository;
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
public class CityServiceImplTest {

    private static final Long ID = 1L;
    private static final String NAME = "name";
    private static final Long WRONG_ID = 2L;
    private static final String CITY_NAME = "cityName";
    private static final String WRONG_NAME = "wrong name";

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldFindAllCities() {
        final City city = generateCity();
        given(cityRepository.findAll()).willReturn(Collections.singletonList(city));

        final List<CityDto> cityDtos = cityService.findAll();
        assertEquals(1, cityDtos.size());

        final CityDto cityDto = cityDtos.get(0);

        verifyResults(city, cityDto);
    }

    @Test
    public void shouldFindById() {
        final City city = generateCity();
        given(cityRepository.findById(ID)).willReturn(Optional.of(city));

        final CityDto cityDto = cityService.findById(ID);

        verifyResults(city, cityDto);
    }

    @Test
    public void shouldThrowRequestedDataNotFoundExceptionWhenCityNotFoundById() {
        expectedException.expect(RequestedDataNotFoundException.class);
        expectedException.expectMessage(String.format("City with id %d is not found", WRONG_ID));

        cityService.findById(WRONG_ID);
    }

    @Test
    public void shouldFindByName() {
        final City city = generateCity();
        given(cityRepository.findByName(CITY_NAME)).willReturn(Optional.of(city));

        final CityDto cityDto = cityService.findByName(CITY_NAME);

        verifyResults(city, cityDto);
    }

    @Test
    public void shouldThrowRequestedDataNotFoundExceptionWhenCityNotFoundByName() {

        expectedException.expect(RequestedDataNotFoundException.class);
        expectedException.expectMessage(String.format("City with name %s is not found", WRONG_NAME));

        cityService.findByName(WRONG_NAME);
    }

    private void verifyResults(City city, CityDto cityDto) {
        assertEquals(city.getId(), cityDto.getId());
        assertEquals(city.getName(), cityDto.getName());
    }

    private City generateCity() {
        final City city = new City();
        city.setId(ID);
        city.setName(NAME);
        return city;
    }

}