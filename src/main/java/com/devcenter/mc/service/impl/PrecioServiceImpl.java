package com.devcenter.mc.service.impl;


import com.devcenter.mc.model.Precio;
import com.devcenter.mc.model.dto.PrecioDTO;
import com.devcenter.mc.repository.PrecioDAO;
import com.devcenter.mc.service.PrecioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrecioServiceImpl implements PrecioService {

    @Autowired
    private PrecioDAO precioDAO;

    @Override
    public List<PrecioDTO> findByIdCocheAndFecha(Long idCoche, Date fechaAplicacion) throws Exception {
        List<PrecioDTO> preciosDTOs = new ArrayList<>();
        Iterable<Precio> precios = precioDAO.findPreciosByCocheIdAndFechaFinIsGreaterThanEqualAndFechaInicioIsLessThan(idCoche, fechaAplicacion, fechaAplicacion);
        precios.forEach(precio -> preciosDTOs.add(new PrecioDTO(precio)));

        return preciosDTOs;
    }
}
