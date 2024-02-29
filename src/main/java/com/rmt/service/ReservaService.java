package com.rmt.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rmt.entities.EstadoReserva;
import com.rmt.entities.Mesa;
import com.rmt.entities.Reserva;
import com.rmt.entities.Usuario;

public interface ReservaService {

	Reserva crearReserva(Long mesaId, Long clienteId, LocalDate fechaReserva, Integer numClientes) throws NotFoundException;
	
	Reserva cancelarReserva(Long reservaId);
	
	Reserva actualizarEstadoReserva(Long reservaId, EstadoReserva nuevoEstado);
	
    Optional<Reserva> obtenerReservaPorId(Long reservaId);
    
    List<Reserva> listarTodasLasReservas();
    
    List<Reserva> listarReservasPorUsuario(Long usuarioId, Pageable pageable);

	Page<Mesa> listarMesasReservadasPorUsuario(Long usuarioId, Pageable pageable);

	Reserva reservarMesa(List<Mesa> mesas, Usuario cliente, Integer numClientes, LocalDate diaReserva);
}
