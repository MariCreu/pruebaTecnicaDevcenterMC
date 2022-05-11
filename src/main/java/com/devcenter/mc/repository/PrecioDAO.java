package com.devcenter.mc.repository;

import com.devcenter.mc.model.Precio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PrecioDAO extends CrudRepository<Precio, Long> {

    Iterable<Precio> findPreciosByCocheIdAndFechaFinIsGreaterThanEqualAndFechaInicioIsLessThan(@Param("idCoche") Long idCoche, @Param("fechaAplicacion") Date fechaAplicacion, @Param("fechaAplicacion") Date fechaAplicacionFin);

    Iterable<Precio> findPreciosByCoche_MarcaNombreMARCA(String paramMarca);

    Iterable<Precio> findPreciosByCoche_NombreModelo(String paramModelo);

    Iterable<Precio> findPreciosByCoche_Color(String paramColor);

    Iterable<Precio> findPreciosByPrecioLessThanEqual(Integer paramPrecio);

    Iterable<Precio> findPreciosByPrecioGreaterThanEqual(Integer paramPrecio);

    Iterable<Precio> findAll();
}
