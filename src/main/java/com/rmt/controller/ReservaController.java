package com.rmt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmt.entities.EstadoReserva;
import com.rmt.entities.Mesa;
import com.rmt.entities.Reserva;
import com.rmt.service.ReservaService;



@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	/*
    @PostMapping("/reservarMesa")
    public ResponseEntity<Reserva> reservarMesa(
            @RequestBody List<Mesa> mesas,
            @RequestParam Long clienteId,
            @RequestParam Integer numClientes,
            @RequestParam LocalDate diaReserva) {
        Usuario cliente = // Obtén el usuario según el clienteId
        Reserva reserva = reservaService.reservarMesa(mesas, cliente, numClientes, diaReserva);
        return new ResponseEntity<>(reserva, HttpStatus.CREATED);
    }
*/
		
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

	    @GetMapping
		@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	    public ResponseEntity<List<Reserva>> listarTodasLasReservas() {
	        List<Reserva> reservas = reservaService.listarTodasLasReservas();
	        return new ResponseEntity<>(reservas, HttpStatus.OK);
	    }

	    @GetMapping("/usuarios/{usuarioId}")
	    public ResponseEntity<List<Reserva>> listarReservasPorUsuario(@PathVariable Long usuarioId) {
	    	Pageable pageable = PageRequest.of(1,5);
	        List<Reserva> reservas = reservaService.listarReservasPorUsuario(usuarioId, pageable);
	        return new ResponseEntity<>(reservas, HttpStatus.OK);
	    }

	    @GetMapping("/listarMesasReservadasPorUsuario/{usuarioId}")
	    public ResponseEntity<Page<Mesa>> listarMesasReservadasPorUsuario(
	            @PathVariable Long usuarioId,
	            Pageable pageable) {
	        Page<Mesa> mesas = reservaService.listarMesasReservadasPorUsuario(usuarioId, pageable);
	        return new ResponseEntity<>(mesas, HttpStatus.OK);
	    }

}
