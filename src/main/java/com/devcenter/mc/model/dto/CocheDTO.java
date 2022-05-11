package com.devcenter.mc.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CocheDTO {

    private String nombreModelo;
    private String color;
    private String marca;
    private Integer precio;
}
