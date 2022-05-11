package com.devcenter.mc.controller;

import com.devcenter.mc.model.dto.CocheDTO;
import com.devcenter.mc.service.ApiCallsService;
import com.devcenter.mc.service.CocheService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CocheControllerTest {

    @Mock
    private CocheService cocheService;
    @Mock
    private ApiCallsService apiCallsService;
    @InjectMocks
    private CocheController underTest;

    @Nested
    class WhenGettingCoches {
        private final String FILTER = "FILTER";
        @Mock
        private HttpServletRequest request;

        @BeforeEach
        void setup() {
        }

        @Test
        public void getCochesListaEmpty() {
            List<CocheDTO> listaCoches = new ArrayList<>();
            when(cocheService.findAll()).thenReturn(listaCoches);

            ResponseEntity<?> response = underTest.getCoches("", request);
            assertEquals(response.getStatusCodeValue(), 200);
            assertEquals(response.getBody(), "No se ha encontrado ningún resultado, inténtelo con otro valor");

        }

        @Test
        public void getCochesFiltroError() {
            List<CocheDTO> listaCoches = new ArrayList<>();
            when(cocheService.findAll()).thenReturn(null);

            ResponseEntity<?> response = underTest.getCoches("", request);
            assertEquals(response.getStatusCodeValue(), 200);
            assertEquals(response.getBody(), "No se ha encontrado ningún resultado, el filtro introducido () no es correcto");
        }

        @Test
        public void getCochesResultadosOK() {
            List<CocheDTO> listaCoches = new ArrayList<>();
            CocheDTO coche = CocheDTO.builder().color("rojo").nombreModelo("ibiza").precio(15000).marca("seat").build();
            listaCoches.add(coche);
            when(cocheService.findAll()).thenReturn(listaCoches);

            ResponseEntity<?> response = underTest.getCoches("", request);
            assertEquals(response.getStatusCodeValue(), 200);
            assertEquals(response.getBody(), listaCoches);

        }
    }

    @Nested
    class WhenGettingExcelListadoCoches {
        @Mock
        private HttpServletRequest request;

        @BeforeEach
        void setup() {
        }

        @Test
        public void getExcelListadoCochesTest() throws IOException {
            List<CocheDTO> listaCoches = new ArrayList<>();
            CocheDTO coche = CocheDTO.builder().color("rojo").nombreModelo("ibiza").precio(15000).marca("seat").build();
            listaCoches.add(coche);
            ByteArrayInputStream stream = new ByteArrayInputStream(new ByteArrayOutputStream().toByteArray());
            when(cocheService.exportToExcel()).thenReturn(stream);

            ResponseEntity<?> response = underTest.getExcelListadoCoches(request);
            assertEquals(response.getStatusCodeValue(), 200);
            assertEquals(response.getHeaders().getContentDisposition().getFilename(), "coches.xls");

        }
    }
}