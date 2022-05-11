package com.devcenter.mc.controller;

import com.devcenter.mc.model.exceptions.CarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CarException> MethodArgumentTypeMismatchException(HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String message;
        if (e.getName().equals("fechaAplicacion")) {
            message = "El parámetro " + e.getName() + " no tiene el tipo requerido (" + e.getRequiredType().getName() + ") en el formato yyyyMMdd";
        } else {
            message = "El parámetro " + e.getName() + " no tiene el tipo requerido (" + e.getRequiredType().getName() + ")";
        }
        CarException errorInfo = new CarException(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<CarException> missingParameters(HttpServletRequest request, MissingServletRequestParameterException e) {
        CarException errorInfo = new CarException("El parámetro  " + e.getParameterName() + " no puede ser nulo, debe ser de tipo " + e.getParameterType(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }


}





