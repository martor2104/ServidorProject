package com.rmt.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.rmt.entities.Usuario;
import com.rmt.entities.Mesa;
import com.rmt.entities.Reserva;
import com.rmt.security.dto.response.error.DetailsResponse;
import com.rmt.security.dto.response.error.ErrorDetailsResponse;
import com.rmt.service.MesaService;
import com.rmt.service.ReservaService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/v1/mesas")
public class MesaController {

	private static final Logger logger =  LoggerFactory.getLogger(MesaController.class);
	
	@Autowired
	private MesaService mesaService;

	
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public ResponseEntity<Page<Mesa>> listarTodasLasMesas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        logger.info("MesaController :: listarTodasLasMesas");
        Pageable pageable = PageRequest.of(page, size);
        Page<Mesa> mesas = mesaService.listarTodasLasMesas(pageable);
             
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public Mesa getTableById(@PathVariable Long id) {
        return mesaService.obtenerMesaPorId(id);
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mesa createTable(@RequestBody Mesa mesa) {
        return mesaService.agregarMesa(mesa);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mesa updateTable(@PathVariable Long id, @RequestBody Mesa mesaDetails) {
        return mesaService.actualizarMesa(id, mesaDetails);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteTable(@PathVariable Long id) {
        mesaService.eliminarMesa(id);
    }
    
    /*
    @PostMapping("/{mesaId}/reservar")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<?> realizarReserva(@PathVariable Long mesaId, @AuthenticationPrincipal Usuario usuario, Integer numeroPersonas) {
    	  try {
    	        logger.info("MesaController :: realizarReserva id Mesa: {} Cliente: {}", mesaId, usuario.getUsername());

    	        LocalDate fechaReserva = LocalDate.now();


    	        Long usuarioId = usuario.getId(); 

    	        Reserva reserva = reservaService.crearReserva(mesaId, usuarioId, fechaReserva, numeroPersonas);


    	        String message = reserva.getMesas().stream()
    	                .map(mesa -> "Reservado: '" + mesa.getNumMesa() + "', " + mesa.getZona() + ", " + mesa.getMaxCliente())
    	                .collect(Collectors.joining(", ")); 

    	        DetailsResponse details_reserva = new DetailsResponse(
    	                new Date(),
    	                message,
    	                "Detalles adicionales aquí"
    	        );

    	        return ResponseEntity.status(HttpStatus.CREATED).body(details_reserva);
          } catch (EntityNotFoundException e) {
        	  ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                      new Date(),
                      "No encontrado",
                      e.getMessage()
              );
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
          } catch (Exception e) {
              ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
                      new Date(),
                      "Error interno del servidor",
                      e.getMessage()
              );
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
          }
      }
    */
}
