package com.andriytrefyak.timetableservice.repository;

import com.andriytrefyak.timetableservice.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(final String name);

}
