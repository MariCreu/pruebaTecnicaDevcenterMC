package com.devcenter.mc.model.dto;

import com.devcenter.mc.model.Precio;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class PrecioDTO {
    private Long idCoche;
    private Long idMarca;
    private Date fechaInicio;
    private Date fechaFin;
    private Integer precio;

    public PrecioDTO(Precio precio) {
        this.idCoche = precio.getCoche().getId();
        this.idMarca = precio.getCoche().getMarca().getId();
        this.fechaInicio = precio.getFechaInicio();
        this.fechaFin = precio.getFechaFin();
        this.precio = precio.getPrecio();
    }
}
