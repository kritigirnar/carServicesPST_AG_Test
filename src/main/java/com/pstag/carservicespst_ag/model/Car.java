package com.pstag.carservicespst_ag.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    private Double length;
    private Double weight;
    private Double velocity;
    private String colour;
}
