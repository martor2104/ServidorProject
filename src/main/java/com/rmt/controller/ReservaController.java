package com.rmt.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmt.entities.EstadoReserva;
import com.rmt.entities.Mesa;
import com.rmt.entities.Reserva;
import com.rmt.entities.Usuario;
import com.rmt.security.dto.response.error.DetailsResponse;
import com.rmt.security.dto.response.error.ErrorDetailsResponse;
import com.rmt.service.ReservaService;

import jakarta.persistence.EntityNotFoundException;



@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	private static final Logger logger =  LoggerFactory.getLogger(MesaController.class);
	
	@PostMapping("/{mesaId}/reservar")
    public ResponseEntity<?> realizarReserva(@AuthenticationPrincipal Usuario usuario, @PathVariable Long mesaId, @RequestBody Integer numeroPersonas) {
    	  try {
    		  
    		  if (usuario == null) {
    	            ErrorDetailsResponse errorDetails = new ErrorDetailsResponse(
    	                    new Date(),
    	                    "Error interno del servidor",
    	                    "El objeto de usuario es nulo"
    	            );
    	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
    	        }
    		  
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
	
	
    @GetMapping
    public ResponseEntity<List<Reserva>> listarTodasLasReservas() {
        List<Reserva> reservas = reservaService.listarTodasLasReservas();
        return new ResponseEntity<>(reservas, HttpStatus.OK);
    }
		
	    @PutMapping("/cancelar/{reservaId}")
		@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	    public ResponseEntity<Reserva> cancelarReserva(@PathVariable Long reservaId) {
	        Reserva reserva = reservaService.cancelarReserva(reservaId);
	        return new ResponseEntity<>(reserva, HttpStatus.OK);
	    }

	    @PutMapping("/actualizarEstado/{reservaId}")
		@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	    public ResponseEntity<Reserva> actualizarEstadoReserva(
	            @PathVariable Long reservaId,
	            @RequestParam EstadoReserva nuevoEstado) {
	        Reserva reserva = reservaService.actualizarEstadoReserva(reservaId, nuevoEstado);
	        return new ResponseEntity<>(reserva, HttpStatus.OK);
	    }

	    @GetMapping("/{reservaId}")
		@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	    public ResponseEntity<Reserva> obtenerReservaPorId(@PathVariable Long reservaId) {
	        Optional<Reserva> reserva = reservaService.obtenerReservaPorId(reservaId);
	        return reserva.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }



	    @GetMapping("/usuarios/{usuarioId}")
	    public ResponseEntity<List<Reserva>> listarReservasPorUsuario(@PathVariable Long usuarioId) {
	        List<Reserva> reservas = reservaService.listarReservasPorUsuario(usuarioId);
	        return new ResponseEntity<>(reservas, HttpStatus.OK);
	    }

	    @GetMapping("/mesas/{reservaId}")
	    public ResponseEntity<List<Mesa>> listarMesasReservadasReserva(@PathVariable Long reservaId) {
	        List<Mesa> mesas = reservaService.listarMesasReservadasPorReserva(reservaId);
	        return new ResponseEntity<>(mesas, HttpStatus.OK);
	    }

}
