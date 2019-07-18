package com.andriytrefyak.searchrouteservice.controller;

import com.andriytrefyak.searchrouteservice.dto.RouteDto;
import com.andriytrefyak.searchrouteservice.service.SearchRouteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("/search-route")
@Validated
public class SearchRouteController {

    @Autowired
    private SearchRouteService searchRouteService;

    @GetMapping("/shortest-route")
    @ApiOperation(value = "Get shortest route", response = RouteDto.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful")
    })
    public List<RouteDto> getShortestRoute(@RequestParam @NotBlank @Length(min = 1, max = 100) final String departureCityName,
                                           @RequestParam @NotBlank @Length(min = 1, max = 100) final String arrivalCityName,
                                           @RequestParam(required = false, defaultValue = "false") final Boolean direct) {
        return searchRouteService.calculateShortestWay(departureCityName, arrivalCityName, direct);
    }
}
