package com.andriytrefyak.timetableservice.mapper;

import com.andriytrefyak.timetableservice.domain.City;
import com.andriytrefyak.timetableservice.dto.CityDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto toCityDTO(final City city);
}
