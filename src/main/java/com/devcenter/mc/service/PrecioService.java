package com.devcenter.mc.service;

import com.devcenter.mc.model.dto.PrecioDTO;

import java.util.Date;
import java.util.List;

public interface PrecioService {

    List<PrecioDTO> findByIdCocheAndFecha(Long idCoche, Date fechaAplicacion) throws Exception;
}
