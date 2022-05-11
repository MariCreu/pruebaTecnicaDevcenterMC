package com.devcenter.mc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRECIOS")
public class Precio implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "FECHA_INICIO")
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    private Date fechaFin;
    @Column(name = "PRECIO")
    private Integer precio;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_COCHE", referencedColumnName = "ID")
    private Coche coche;


}
