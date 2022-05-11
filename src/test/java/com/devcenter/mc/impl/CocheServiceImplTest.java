package com.devcenter.mc.impl;

import com.devcenter.mc.model.Coche;
import com.devcenter.mc.model.Marca;
import com.devcenter.mc.model.Precio;
import com.devcenter.mc.model.dto.CocheDTO;
import com.devcenter.mc.repository.CocheDAO;
import com.devcenter.mc.repository.PrecioDAO;
import com.devcenter.mc.service.impl.CocheServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CocheServiceImplTest {

    @Mock
    private CocheDAO cocheDao;
    @Mock
    private PrecioDAO precioDAO;
    @InjectMocks
    private CocheServiceImpl underTest;

    @Nested
    class WhenFindingAll {
        @BeforeEach
        void setup() {
        }
    }

    @Nested
    class WhenGettingByFilter {
        private final String FILTER = "FILTER";

        @BeforeEach
        void setup() {
        }

    }

    @Nested
    class WhenExportingToExcel {
        @BeforeEach
        void setup() {
        }
    }

    @Test
    void findAll() {
        Marca marca = new Marca(1L, "Seat");
        Coche coche = new Coche(1L, "modelo", "rojo", marca);
        Precio precio = new Precio(1L, new Date(20220101), new Date(20220606), 15000, coche);
        Iterable<Precio> precios = List.of(precio);
        when(precioDAO.findAll()).thenReturn(precios);
        List<CocheDTO> preciosDTO = this.underTest.findAll();
        assertEquals(preciosDTO.size(), 1);
        assertEquals(preciosDTO.get(0).getPrecio(), 15000);
    }

    @Test
    void getByFilterMarca() {

        Marca marca = new Marca(1L, "Seat");
        Coche coche = new Coche(1L, "modelo", "rojo", marca);
        Precio precio = new Precio(1L, new Date(20220101), new Date(20220606), 15000, coche);
        Iterable<Precio> precios = List.of(precio);
        when(precioDAO.findPreciosByCoche_MarcaNombreMARCA(any())).thenReturn(precios);
        List<CocheDTO> preciosDTO = this.underTest.getByFilter("marca eq Seat");
        assertEquals(preciosDTO.size(), 1);
        assertEquals(preciosDTO.get(0).getPrecio(), 15000);
    }

    @Test
    void getByFilterModelo() {

        Marca marca = new Marca(1L, "Seat");
        Coche coche = new Coche(1L, "modelo", "rojo", marca);
        Precio precio = new Precio(1L, new Date(20220101), new Date(20220606), 15000, coche);
        Iterable<Precio> precios = List.of(precio);
        when(precioDAO.findPreciosByCoche_NombreModelo(any())).thenReturn(precios);
        List<CocheDTO> preciosDTO = this.underTest.getByFilter("modelo eq modelo");
        assertEquals(preciosDTO.size(), 1);
        assertEquals(preciosDTO.get(0).getPrecio(), 15000);
    }

    @Test
    void getByFilterColor() {

        Marca marca = new Marca(1L, "Seat");
        Coche coche = new Coche(1L, "modelo", "rojo", marca);
        Precio precio = new Precio(1L, new Date(20220101), new Date(20220606), 15000, coche);
        Iterable<Precio> precios = List.of(precio);
        when(precioDAO.findPreciosByCoche_Color(any())).thenReturn(precios);
        List<CocheDTO> preciosDTO = this.underTest.getByFilter("color eq rojo");
        assertEquals(preciosDTO.size(), 1);
        assertEquals(preciosDTO.get(0).getPrecio(), 15000);
    }

    @Test
    void getByFilterPrecioMenor() {

        Marca marca = new Marca(1L, "Seat");
        Coche coche = new Coche(1L, "modelo", "rojo", marca);
        Precio precio = new Precio(1L, new Date(20220101), new Date(20220606), 15000, coche);
        Iterable<Precio> precios = List.of(precio);
        when(precioDAO.findPreciosByPrecioLessThanEqual(any())).thenReturn(precios);
        List<CocheDTO> preciosDTO = this.underTest.getByFilter("precio lt 15000");
        assertEquals(preciosDTO.size(), 1);
        assertEquals(preciosDTO.get(0).getPrecio(), 15000);
    }

    @Test
    void getByFilterPrecioMayor() {

        Marca marca = new Marca(1L, "Seat");
        Coche coche = new Coche(1L, "modelo", "rojo", marca);
        Precio precio = new Precio(1L, new Date(20220101), new Date(20220606), 15000, coche);
        Iterable<Precio> precios = List.of(precio);
        when(precioDAO.findPreciosByPrecioGreaterThanEqual(any())).thenReturn(precios);
        List<CocheDTO> preciosDTO = this.underTest.getByFilter("precio gt 15000");
        assertEquals(preciosDTO.size(), 1);
        assertEquals(preciosDTO.get(0).getPrecio(), 15000);
    }

    @Test
    void exportToExcel() throws IOException {

        Marca marca = new Marca(1L, "Seat");
        Coche coche = new Coche(1L, "modelo", "rojo", marca);
        List<Coche> coches = List.of(coche);
        when(cocheDao.findAll()).thenReturn(coches);
        ByteArrayInputStream excel = this.underTest.exportToExcel();
        assertEquals(excel.available(), 4096);
    }
}