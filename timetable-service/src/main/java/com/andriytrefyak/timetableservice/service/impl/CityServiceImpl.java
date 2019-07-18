package com.andriytrefyak.timetableservice.service.impl;

import com.andriytrefyak.timetableservice.dto.CityDto;
import com.andriytrefyak.timetableservice.exception.RequestedDataNotFoundException;
import com.andriytrefyak.timetableservice.mapper.CityMapper;
import com.andriytrefyak.timetableservice.repository.CityRepository;
import com.andriytrefyak.timetableservice.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityDto findById(final Long id) {
        return cityRepository.findById(id).map(CityMapper.INSTANCE::toCityDTO).orElseThrow(() ->
                new RequestedDataNotFoundException(String.format("City with id %d is not found", id)));
    }

    @Override
    public CityDto findByName(final String name) {
        return cityRepository.findByName(name).map(CityMapper.INSTANCE::toCityDTO).orElseThrow(() ->
                new RequestedDataNotFoundException(String.format("City with name %s is not found", name)));
    }

    @Override
    public List<CityDto> findAll() {
        return cityRepository.findAll().stream().map(CityMapper.INSTANCE::toCityDTO).collect(Collectors.toList());
    }
}
