package com.pstag.carservicespst_ag.Validations;

import lombok.Data;

@Data
public class CustomGlobalException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

}
