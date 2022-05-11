package com.devcenter.mc.model.exceptions;

import lombok.Data;


@Data
public class CarException {
    private String message;
    private Integer statusCode;

    public CarException(String message, Integer statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }


}
