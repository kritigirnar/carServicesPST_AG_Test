package com.pstag.carservicespst_ag.service;


import com.pstag.carservicespst_ag.dto.CarDTO;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public interface CarService {

    CarDTO saveCar(@Valid CarDTO car);

    CarDTO updateCar(@Valid CarDTO car);

    List<CarDTO> searchCars(Double length, Double weight, Double velocity, String colour);
}
