package com.pstag.carservicespst_ag.controller;


import com.pstag.carservicespst_ag.dto.CarDTO;
import com.pstag.carservicespst_ag.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cars")
@Validated
@RestController
public class CarController {

    @Autowired
    private CarService carService;
    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @PostMapping
    public CarDTO createCar(@RequestBody CarDTO car) {

        logger.info("Adding new car: {}", car.toString());
        CarDTO  savedCar = carService.saveCar(car);
        logger.info("Car created with ID: {}", savedCar.getId());

        return savedCar;
    }

    @PutMapping
    public CarDTO updateCar(@RequestBody CarDTO car) {

        logger.info("Updating car: {}", car);
        CarDTO updatedCar = carService.updateCar(car);
        logger.info("Car updated with ID: {}", updatedCar.getId());

        return updatedCar;
    }

    @GetMapping(value = "/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<CarDTO> searchCar(@RequestParam(required = false) Double length,
                                  @RequestParam(required = false) Double weight,
                                  @RequestParam(required = false) Double velocity,
                                  @RequestParam(required = false) String colour) {

        logger.info("Searching cars with length: {}, weight: {}, velocity: {}, colour: {}",
                length, weight, velocity, colour);
        List<CarDTO> cars = carService.searchCars(length, weight, velocity, colour);
        logger.info("Found {} cars", cars.size());

        return cars;

    }

}
