package com.devcenter.mc.controller;

import com.devcenter.mc.model.ServiceEnum;
import com.devcenter.mc.model.dto.CocheDTO;
import com.devcenter.mc.model.exceptions.CarException;
import com.devcenter.mc.service.ApiCallsService;
import com.devcenter.mc.service.CocheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/coches")
public class CocheController {

    @Autowired
    private CocheService cocheService;
    @Autowired
    private ApiCallsService apiCallsService;

    @GetMapping
    public ResponseEntity<?> getCoches(@Param(value = "filter") String filter, HttpServletRequest request) {

        try {
            apiCallsService.saveApiCalls(request, ServiceEnum.SERVICE_COCHES);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new CarException("Se ha producido un error al guardar el log del servicio", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        try {
            List<CocheDTO> listaCoches;
            if (filter == null || filter.equals("")) {
                listaCoches = cocheService.findAll();

            } else {
                listaCoches = cocheService.getByFilter(filter);
            }
            if (listaCoches == null || listaCoches.isEmpty()) {
                String message = "No se ha encontrado ningún resultado";
                if (listaCoches == null) {
                    message += ", el filtro introducido (" + filter + ") no es correcto";
                } else {
                    message += ", inténtelo con otro valor";
                }
                return ResponseEntity.ok(message);
            }
            return ResponseEntity.ok(listaCoches);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CarException("Se ha producido un error al buscar el listado de coches", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
    }


    @GetMapping(value = "/excel")
    ResponseEntity<?> getExcelListadoCoches(HttpServletRequest request) {

        try {
            apiCallsService.saveApiCalls(request, ServiceEnum.SERVICE_COCHES);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new CarException("Se ha producido un error al guardar el log del servicio", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        ByteArrayInputStream stream;
        try {
            stream = cocheService.exportToExcel();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CarException("Se ha producido un error al exportar a excel los datos", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "attachment; filename=coches.xls");

        return ResponseEntity.ok().headers(httpHeaders).body(new InputStreamResource(stream));
    }
}
