package com.pstag.carservicespst_ag.service;



import com.pstag.carservicespst_ag.Validations.CarValidator;
import com.pstag.carservicespst_ag.Validations.CustomGlobalException;
import com.pstag.carservicespst_ag.dto.CarDTO;
import com.pstag.carservicespst_ag.exception.CustomGlobalMessages;
import com.pstag.carservicespst_ag.mapper.CarMapper;
import com.pstag.carservicespst_ag.model.Car;
import com.pstag.carservicespst_ag.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class CarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    public CarDTO saveCar(CarDTO carDTO) {

        logger.info("Saving Car Details: {}", carDTO);
        CarValidator.validateCarService(carDTO);

        try {
            Car car = carMapper.toEntity(carDTO);
            return carMapper.toDTO(carRepository.save(car));
        } catch (Exception e) {
            throw new CustomGlobalException(CustomGlobalMessages.ERROR_OCCURED_WHILE_SAVING_CODE,
                    CustomGlobalMessages.ERROR_OCCURED_WHILE_SAVING_MESSAGE);
        }
    }


    @Override
    public CarDTO updateCar(CarDTO carDTO) {

        logger.info("Updating Car: {}", carDTO);
        CarValidator.validateCarUpdateService(carDTO);

        try {
            Car car = carMapper.toEntity(carDTO);
            return carMapper.toDTO(carRepository.save(car)); }

        catch (Exception e) {
            throw new RuntimeException("Failed to update car", e);
        }
    }


    @Override
    public List<CarDTO> searchCars(Double length, Double weight, Double velocity, String colour) {

        logger.info("Searching cars with differnt criteria length: {}, weight: {}, velocity: {}, colour: {}",
                length, weight, velocity, colour);
        try {
            List<Car> cars = carRepository.searchCars(length, weight, velocity, colour);
            logger.info("Found {} cars", cars.size());
            return cars.stream()
                    .map(carMapper::toDTO)
                    .collect(Collectors.toList());}

        catch (Exception e) {
            throw new CustomGlobalException(CustomGlobalMessages.ERROR_OCCURED_WHILE_SEARCHING_CODE,
                    CustomGlobalMessages.ERROR_OCCURED_WHILE_SEARCHING_MESSAGE);
        }
    }

}
