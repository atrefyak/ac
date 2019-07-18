package com.andriytrefyak.timetableservice.service;

import com.andriytrefyak.timetableservice.dto.RouteDto;

import java.util.List;

public interface RouteService {

    List<RouteDto> findAll();

    List<RouteDto> findByDepartureCityName(final String departureCityName);

    List<RouteDto> findByDepartureCityId(final Long departureCityId);
}
