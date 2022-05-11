package com.devcenter.mc.controller;

import com.devcenter.mc.model.dto.PrecioDTO;
import com.devcenter.mc.service.ApiCallsService;
import com.devcenter.mc.service.PrecioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PrecioControllerTest {

    @Mock
    private PrecioService precioService;
    @Mock
    private ApiCallsService apiCallsService;
    @InjectMocks
    private PrecioController underTest;

    @Nested
    class WhenGettingPrecios {
        @Mock
        private Date fechaAplicacion;
        @Mock
        private HttpServletRequest request;

        @BeforeEach
        void setup() {
        }

        @Test
        void getPreciosOK() throws Exception {
            List<PrecioDTO> precioDTOS = new ArrayList<>();
            when(precioService.findByIdCocheAndFecha(any(), any())).thenReturn(precioDTOS);

            ResponseEntity<?> responseEntity = underTest.getPrecios(fechaAplicacion, 1L, request);
            assertEquals(responseEntity.getStatusCodeValue(), 200);
        }
    }
}