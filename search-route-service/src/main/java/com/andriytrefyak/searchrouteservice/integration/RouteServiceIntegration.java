package com.andriytrefyak.searchrouteservice.integration;

import com.andriytrefyak.searchrouteservice.dto.RouteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("http://timetable-service")
public interface RouteServiceIntegration {

    @GetMapping("/routes")
    List<RouteDto> getAllRoutes();

    @GetMapping(value = "/routes/{id}")
    List<RouteDto> getRoutesByDepartureCityId(@PathVariable Long id);

    @GetMapping(value = "/routes")
    List<RouteDto> getRoutesByDepartureCityName(@RequestParam(name = "departureCityName") String departureCityName);
}
