package com.devcenter.mc.controller;


import com.devcenter.mc.model.ServiceEnum;
import com.devcenter.mc.model.dto.PrecioDTO;
import com.devcenter.mc.model.exceptions.CarException;
import com.devcenter.mc.service.ApiCallsService;
import com.devcenter.mc.service.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/precios")
public class PrecioController {

    @Autowired
    private PrecioService precioService;
    @Autowired
    private ApiCallsService apiCallsService;


    @GetMapping
    public ResponseEntity<?> getPrecios(@RequestParam(value = "fechaAplicacion") @DateTimeFormat(pattern = "yyyyMMdd") Date fechaAplicacion,
                                        @RequestParam(value = "idCoche") Long idCoche, HttpServletRequest request) {
        try {
            apiCallsService.saveApiCalls(request, ServiceEnum.SERVICE_COCHES);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new CarException("Se ha producido un error al guardar el log del servicio", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        try {
            List<PrecioDTO> precioDTOS = precioService.findByIdCocheAndFecha(idCoche, fechaAplicacion);
            return ResponseEntity.ok(precioDTOS);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new CarException("Se ha producido un error al buscar los precios", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }


}
