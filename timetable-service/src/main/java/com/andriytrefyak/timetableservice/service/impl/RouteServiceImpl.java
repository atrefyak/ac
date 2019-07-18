package com.andriytrefyak.timetableservice.service.impl;

import com.andriytrefyak.timetableservice.domain.City;
import com.andriytrefyak.timetableservice.dto.RouteDto;
import com.andriytrefyak.timetableservice.exception.RequestedDataNotFoundException;
import com.andriytrefyak.timetableservice.mapper.RouteMapper;
import com.andriytrefyak.timetableservice.repository.CityRepository;
import com.andriytrefyak.timetableservice.repository.RouteRepository;
import com.andriytrefyak.timetableservice.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl  implements RouteService {

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<RouteDto> findAll() {
        return routeRepository.findAll().stream().map(RouteMapper.INSTANCE::toRouteDTO).collect(Collectors.toList());
    }

    @Override
    public List<RouteDto> findByDepartureCityName(final String departureCityName) {
        final City city = cityRepository.findByName(departureCityName).orElseThrow(() ->
            new RequestedDataNotFoundException(String.format("City with name %s is not found", departureCityName)));
        return routeRepository.findByDepartureCity(city).stream().map(RouteMapper.INSTANCE::toRouteDTO).collect(Collectors.toList());
    }

    @Override
    public List<RouteDto> findByDepartureCityId(final Long departureCityId) {
        final City city = cityRepository.findById(departureCityId).orElseThrow(() -> new RequestedDataNotFoundException(String.format("City with id %d is not found", departureCityId)));
        return routeRepository.findByDepartureCity(city).stream().map(RouteMapper.INSTANCE::toRouteDTO).collect(Collectors.toList());
    }
}
