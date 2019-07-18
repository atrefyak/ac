package com.andriytrefyak.timetableservice.controller;

import com.andriytrefyak.timetableservice.dto.CityDto;
import com.andriytrefyak.timetableservice.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@Api
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    @ApiOperation(value = "Get All cities", response = CityDto.class, responseContainer = "List")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful")})
    public List<CityDto> getAllCities() {
        return cityService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get city by id", response = CityDto.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful")})
    public CityDto getCity(@PathVariable final Long id) {
        return cityService.findById(id);
    }

    @GetMapping(value = "/", params = "name")
    @ApiOperation(value = "Get city by name", response = CityDto.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful")})
    public CityDto getCity(@RequestParam(name = "name") final String name) {
        return cityService.findByName(name);
    }
}
