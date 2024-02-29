package com.rmt.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.rmt.entities.Usuario;
import com.rmt.entities.EstadoReserva;
import com.rmt.entities.Mesa;
import com.rmt.entities.Reserva;
import com.rmt.repository.MesaRepository;
import com.rmt.repository.ReservaRepository;
import com.rmt.repository.UserRepository;
import com.rmt.service.MesaService;

import jakarta.validation.Valid;

@Service
public class MesaServiceImpl implements MesaService{
	
	@Autowired
	private MesaRepository mesaRepository;
	
    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UserRepository usuarioRepository;

	@Override
	public Mesa agregarMesa(Mesa mesa) {

		return mesaRepository.save(mesa);
	}

	@Override
	public Page<Mesa> listarTodasLasMesas(Pageable pageable) {
        return mesaRepository.findAll(pageable);
	}

	@Override
	public Mesa obtenerMesaPorId(Long id) {
	      return mesaRepository.findById(id).orElse(null);
	}

	@Override
	public Mesa actualizarMesa(Long id, Mesa mesa) {
        if (mesaRepository.existsById(id)) {
            mesa.setId(id);
            return mesaRepository.save(mesa);
        }
        return null;
	}

	@Override
	public void eliminarMesa(Long id) {
        mesaRepository.deleteById(id);
		
	}

	@Override
	public Page<Mesa> listarMesasReservadasPorUsuario(Long usuarioId, Pageable pageable) {
		return mesaRepository.findByReservasClienteId(usuarioId, pageable);
	}
	 
}
