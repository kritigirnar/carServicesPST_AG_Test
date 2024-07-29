package com.pstag.carservicespst_ag.mapper;


import com.pstag.carservicespst_ag.dto.CarDTO;
import com.pstag.carservicespst_ag.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public Car toEntity(CarDTO carDTO) {
        Car car = new Car();
        car.setId(carDTO.getId());
        car.setLength(carDTO.getLength());
        car.setWeight(carDTO.getWeight());
        car.setVelocity(carDTO.getVelocity());
        car.setColour(carDTO.getColour());
        return car;
    }

    public CarDTO toDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(car.getId());
        carDTO.setLength(car.getLength());
        carDTO.setWeight(car.getWeight());
        carDTO.setVelocity(car.getVelocity());
        carDTO.setColour(car.getColour());
        return carDTO;
    }
}
