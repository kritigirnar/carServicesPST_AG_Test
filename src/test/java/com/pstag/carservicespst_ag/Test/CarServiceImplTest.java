package com.pstag.carservicespst_ag.Test;


import com.pstag.carservicespst_ag.dto.CarDTO;
import com.pstag.carservicespst_ag.mapper.CarMapper;
import com.pstag.carservicespst_ag.model.Car;
import com.pstag.carservicespst_ag.repository.CarRepository;
import com.pstag.carservicespst_ag.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCar() {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(1L);
        carDTO.setLength(5.0);
        carDTO.setWeight(1500.0);
        carDTO.setVelocity(200.0);
        carDTO.setColour("red");

        Car car = new Car();
        car.setId(1L);
        when(carMapper.toEntity(carDTO)).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);
        when(carMapper.toDTO(car)).thenReturn(carDTO);

        CarDTO savedCar = carService.saveCar(carDTO);

        assertNotNull(savedCar);
        assertEquals(1L, savedCar.getId());
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void testUpdateCar() {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(1L);
        Car car = new Car();
        car.setId(1L);

        when(carMapper.toEntity(carDTO)).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);
        when(carMapper.toDTO(car)).thenReturn(carDTO);

        CarDTO updatedCar = carService.updateCar(carDTO);
        assertNotNull(updatedCar);
        assertEquals(1L, updatedCar.getId());
        verify(carRepository, times(1)).save(car);
    }

    @Test
    void testSearchCars() {
        Double length = 5.0;
        Double weight = 1500.0;
        Double velocity = 200.0;
        String colour = "red";

        Car car = new Car();
        car.setId(1L);

        CarDTO carDTO = new CarDTO();
        carDTO.setId(1L);
        List<Car> carList = Collections.singletonList(car);
        when(carRepository.searchCars(length, weight, velocity, colour)).thenReturn(carList);
        when(carMapper.toDTO(car)).thenReturn(carDTO);
        List<CarDTO> result = carService.searchCars(length, weight, velocity, colour);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(carRepository, times(1)).searchCars(length, weight, velocity, colour);
    }
}
