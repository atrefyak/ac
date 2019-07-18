package com.andriytrefyak.searchrouteservice.integration;

import com.andriytrefyak.searchrouteservice.dto.CityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("http://timetable-service")
public interface CityServiceIntegration {

    @GetMapping(value = "/cities")
    List<CityDto> getAllCities();

    @GetMapping(value = "/cities/{id}")
    CityDto getCityById(@RequestParam("id") final Long id);

    @GetMapping(value = "/cities")
    CityDto getCityByName(@RequestParam("name") final String name);
}
