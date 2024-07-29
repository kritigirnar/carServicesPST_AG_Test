package com.pstag.carservicespst_ag.Validations;


import com.pstag.carservicespst_ag.dto.CarDTO;
import com.pstag.carservicespst_ag.exception.CustomGlobalMessages;
import org.springframework.util.ObjectUtils;
public class CarValidator {

    public static void validateCarService(CarDTO car) {
        if (car.getLength() == null || car.getLength() <= 0 ||
                car.getWeight() == null || car.getWeight() <= 0 ||
                car.getVelocity() == null || car.getVelocity() <= 0) {
            throw new CustomGlobalException(CustomGlobalMessages.INVALID_CAR_PARAMETERS_ERROR_CODE,
                    CustomGlobalMessages.INVALID_CAR_PARAMETERS_ERROR_MESSAGE);
        }
    }

    public static void validateCarUpdateService(CarDTO car) {
        if (ObjectUtils.isEmpty(car.getId())) {
            throw new CustomGlobalException(CustomGlobalMessages.INVALID_CAR_ID_ERROR_CODE,
                    CustomGlobalMessages.INVALID_CAR_ID_ERROR_MESSAGE);
        }
    }
}

