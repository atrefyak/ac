package com.andriytrefyak.timetableservice.mapper;

import com.andriytrefyak.timetableservice.domain.Route;
import com.andriytrefyak.timetableservice.dto.RouteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteMapper {

    RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

    @Mappings({
            @Mapping(target = "departureCity", source = "departureCity.id"),
            @Mapping(target = "arrivalCity", source = "arrivalCity.id")
    })
    RouteDto toRouteDTO(final Route route);
}
