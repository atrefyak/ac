package com.andriytrefyak.timetableservice.service;

import com.andriytrefyak.timetableservice.dto.CityDto;

import java.util.List;

public interface CityService {

    CityDto findById(final Long id);

    CityDto findByName(final String name);

    List<CityDto> findAll();
}
