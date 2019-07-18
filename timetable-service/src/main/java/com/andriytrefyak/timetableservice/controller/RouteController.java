package com.andriytrefyak.timetableservice.controller;

import com.andriytrefyak.timetableservice.dto.RouteDto;
import com.andriytrefyak.timetableservice.service.RouteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping
    @ApiOperation(value = "Get All routes", response = RouteDto.class, responseContainer = "List")
    @ApiResponses({@ApiResponse(code = 200, message = "Succesfull")})
    public List<RouteDto> getRoutes() {
        return routeService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get route by departure city id", response = RouteDto.class, responseContainer = "List")
    @ApiResponses({@ApiResponse(code = 200, message = "Succesfull")})
    public List<RouteDto> getRoutes(@PathVariable final Long id) {
        return routeService.findByDepartureCityId(id);
    }

    @GetMapping(value = "/", params = "departureCityName")
    @ApiResponses({@ApiResponse(code = 200, message = "Succesfull")})
    @ApiOperation(value = "Get route by departure city name", response = RouteDto.class, responseContainer = "List")
    public List<RouteDto> getRoutes(@RequestParam final String departureCityName) {
        return routeService.findByDepartureCityName(departureCityName);
    }
}
