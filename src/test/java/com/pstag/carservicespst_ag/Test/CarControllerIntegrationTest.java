package com.pstag.carservicespst_ag.Test;


import com.pstag.carservicespst_ag.model.Car;
import com.pstag.carservicespst_ag.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CarRepository carRepository;

    @Test
    void testCreateCar() throws Exception {
        String carJson = "{ \"length\": 4.5, \"weight\": 1000.0, \"velocity\": 180.0, \"colour\": \"blue\" }";

        mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testUpdateCar() throws Exception {
        Car car = new Car();
        car.setLength(4.5);
        car.setWeight(1000.0);
        car.setVelocity(180.0);
        car.setColour("blue");
        car = carRepository.save(car);

        String carJson = "{ \"id\": " + car.getId() + ", \"length\": 4.5, \"weight\": 1000.0, \"velocity\": 180.0, \"colour\": \"blue\" }";
        mockMvc.perform(put("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(carJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testSearchCar() throws Exception {
        Car car = new Car();
        car.setLength(4.5);
        car.setWeight(1000.0);
        car.setVelocity(180.0);
        car.setColour("blue");
        carRepository.save(car);

        mockMvc.perform(get("/cars/search")
                        .param("length", "4.5")
                        .param("weight", "1000.0")
                        .param("velocity", "180.0")
                        .param("colour", "blue")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testSearchCarXml() throws Exception {
        Car car = new Car();
        car.setLength(4.5);
        car.setWeight(1000.0);
        car.setVelocity(180.0);
        car.setColour("blue");
        carRepository.save(car);

        mockMvc.perform(get("/cars/search")
                        .param("length", "4.5")
                        .param("weight", "1000.0")
                        .param("velocity", "180.0")
                        .param("colour", "blue")
                        .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
