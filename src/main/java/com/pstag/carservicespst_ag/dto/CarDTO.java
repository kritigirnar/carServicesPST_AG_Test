package com.pstag.carservicespst_ag.dto;



import com.pstag.carservicespst_ag.markerInterface.CreateCarMarker;
import com.pstag.carservicespst_ag.markerInterface.UpdateCarMarker;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CarDTO {

    @NotNull(message = "Car ID must be present for update.", groups = UpdateCarMarker.class)
    private Long id;

    @NotNull(groups = CreateCarMarker.class)
    private Double length;

    @NotNull(groups = CreateCarMarker.class)
    private Double weight;

    @NotNull(groups = CreateCarMarker.class)
    private Double velocity;

    @NotNull(groups = CreateCarMarker.class)
    private String colour;

}
