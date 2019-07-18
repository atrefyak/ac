package com.andriytrefyak.timetableservice.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name ="name", nullable = false)
    String name;

    @OneToMany(mappedBy = "departureCity")
    private Collection<Route> routes;

}
