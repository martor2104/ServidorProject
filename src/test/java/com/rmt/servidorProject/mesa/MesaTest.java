package com.rmt.servidorProject.mesa;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rmt.controller.MesaController;
import com.rmt.entities.Mesa;
import com.rmt.service.MesaService;

@SpringBootTest
class MesaTest {

	 @Test
	    public void testListarTodasLasMesas() {
	        // Mock del servicio MesaService
		 MesaService mesaServiceMock = mock(MesaService.class);
	        
	        // Crear instancias de Mesas para simular la respuesta del servicio
	        List<Mesa> mesasList = new ArrayList<>();
	        mesasList.add(new Mesa(null, 2, 5, "Terraza", null));
	        mesasList.add(new Mesa(null, 2, 6, "Terraza", null));
	        Page<Mesa> mesasPage = new PageImpl<>(mesasList);

	        // Configurar el comportamiento del servicio mock
	        when(mesaServiceMock.listarTodasLasMesas(any())).thenReturn(mesasPage);

	        // Crear instancia del controlador
	        MesaController mesaController = new MesaController(mesaServiceMock);

	        // Ejecutar el método del controlador
	        ResponseEntity<Page<Mesa>> responseEntity = mesaController.listarTodasLasMesas(0, 10);

	        // Verificar la respuesta
	        assertSame(HttpStatus.OK, responseEntity.getStatusCode());
	        assertSame(mesasPage, responseEntity.getBody());
	    }

	    @Test
	    public void testGetTableById() {
	        // Mock del servicio MesaService
	        MesaService mesaServiceMock = mock(MesaService.class);

	        // Crear una instancia de Mesa para simular la respuesta del servicio
	        Mesa mesa = new Mesa(null, 2, 5, "Terraza", null);

	        // Configurar el comportamiento del servicio mock
	        when(mesaServiceMock.obtenerMesaPorId(1L)).thenReturn(mesa);

	        // Crear instancia del controlador
	        MesaController mesaController = new MesaController(mesaServiceMock);

	        // Ejecutar el método del controlador
	        Mesa result = mesaController.getTableById(1L);

	        // Verificar el resultado
	        assertSame(mesa, result);
	    }
}
