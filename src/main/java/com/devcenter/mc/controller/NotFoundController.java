package com.devcenter.mc.controller;

import com.devcenter.mc.model.exceptions.CarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/error")
public class NotFoundController {

    @GetMapping
    public ResponseEntity getError(Exception e) {
        CarException errorInfo = new CarException("Url introducida incorrecta", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }


}
