package com.devcenter.mc.impl;

import com.devcenter.mc.model.Coche;
import com.devcenter.mc.model.Marca;
import com.devcenter.mc.model.Precio;
import com.devcenter.mc.model.dto.PrecioDTO;
import com.devcenter.mc.repository.PrecioDAO;
import com.devcenter.mc.service.impl.PrecioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrecioServiceImplTest {

    @Mock
    private PrecioDAO precioDAO;
    @InjectMocks
    private PrecioServiceImpl underTest;

    @Nested
    class WhenFindingByIdCocheAndFecha {
        @Mock
        private Date fechaAplicacion;

        @BeforeEach
        void setup() {
        }

    }

    @Test
    void findByIdCocheAndFecha() throws Exception {
        List<PrecioDTO> preciosDTOs = new ArrayList<>();
        Marca marca = new Marca(1L, "Seat");
        Coche coche = new Coche(1L, "modelo", "rojo", marca);
        Precio precio = new Precio(1L, new Date(20220101), new Date(20220606), 15000, coche);
        Iterable<Precio> precios = List.of(precio);

        when(precioDAO.findPreciosByCocheIdAndFechaFinIsGreaterThanEqualAndFechaInicioIsLessThan(any(), any(), any()))
                .thenReturn(precios);

        preciosDTOs = this.underTest.findByIdCocheAndFecha(1L, new Date(20220303));
        assertEquals(preciosDTOs.size(), 1);
        assertEquals(preciosDTOs.get(0).getPrecio(), 15000);
    }
}