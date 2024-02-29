package com.rmt.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rmt.entities.EstadoReserva;
import com.rmt.entities.Mesa;
import com.rmt.entities.Reserva;
import com.rmt.entities.Usuario;
import com.rmt.repository.MesaRepository;
import com.rmt.repository.ReservaRepository;
import com.rmt.repository.UserRepository;
import com.rmt.service.ReservaService;


@Service
public class ReservaServiceImpl implements ReservaService{

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private MesaRepository mesaRepository;
	
	@Autowired
	private UserRepository clienteRepository;

	@Override
	public Reserva crearReserva(Long mesaId, Long clienteId, LocalDate fechaReserva, Integer numClientes) throws NotFoundException {
		
		List<Mesa> mesas = mesaRepository.findAll();

		if (mesas.isEmpty()) {
		    throw new NotFoundException();
		}
/*
	    Usuario cliente = clienteRepository.findById(clienteId)
	    		.orElseThrow(null);
*/

	    Reserva reserva = new Reserva();
	    reserva.setMesas(mesas);
	    //reserva.setCliente(cliente);
	    reserva.setHorario(fechaReserva);
	    reserva.setNumClientes(numClientes);
	    reserva.setEstadoReserva(EstadoReserva.CONFIRMADA);

	    return reservaRepository.save(reserva);
	}

	@Override
	public Reserva cancelarReserva(Long reservaId) {

	    // Cargar la reserva desde la base de datos
	    Reserva reserva = reservaRepository.findById(reservaId)
	            .orElseThrow(null);

	    // Actualizar el estado de la reserva a CANCELADA
	    reserva.setEstadoReserva(EstadoReserva.CANCELADA);


	    return reservaRepository.save(reserva);
	}

	@Override
	public Reserva actualizarEstadoReserva(Long reservaId, EstadoReserva nuevoEstado) {
	    // Verificar si la reserva existe
	    Reserva reserva = reservaRepository.findById(reservaId)
	            .orElseThrow(null);

	    // Actualizar el estado de la reserva
	    reserva.setEstadoReserva(nuevoEstado);

	    // Guardar la reserva actualizada en la base de datos
	    return reservaRepository.save(reserva);
	}

	@Override
	public Optional<Reserva> obtenerReservaPorId(Long reservaId) {
		return reservaRepository.findById(reservaId);
	}

	@Override
	public List<Reserva> listarTodasLasReservas() {
		return reservaRepository.findAll();
	}

	@Override
	public List<Reserva> listarReservasPorUsuario(Long usuarioId, Pageable pageable) {
	    return reservaRepository.findByCliente(usuarioId, pageable);
	}
	
	/*
	@Override
    public Page<Mesa> listarMesasReservadasPorUsuario(Long usuarioId, Pageable pageable) {
        return mesaRepository.findByReservasClienteId(usuarioId, pageable);
    }

    @Override
    public Reserva reservarMesa(List<Mesa> mesas, Usuario cliente, Integer numClientes, LocalDate diaReserva) {

        Reserva reserva = new Reserva();
        reserva.setMesas(mesas);
        reserva.setCliente(cliente);
        reserva.setEstadoReserva(EstadoReserva.CONFIRMADA);
        reserva.setNumClientes(numClientes);
        reserva.setHorario(diaReserva);
        
        Reserva reservaGuardada = reservaRepository.save(reserva);

        return reservaGuardada;
    }
	*/


	@Override
	public Page<Mesa> listarMesasReservadasPorUsuario(Long usuarioId, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reserva reservarMesa(List<Mesa> mesas, Usuario cliente, Integer numClientes, LocalDate diaReserva) {
		// TODO Auto-generated method stub
		return null;
	}
}
