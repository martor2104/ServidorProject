package com.rmt.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmt.entities.Reserva;
import com.rmt.entities.Usuario;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{


	@Query("SELECT r FROM Reserva r WHERE r.cliente = :cliente")
    List<Reserva> findByCliente(@Param("cliente") Usuario user);

	// List<Reserva> findByCliente(@Param("clienteId") Long clienteId);
	/*
	boolean existsByMesaAndUsuarioAndEstadoReserva(Mesa mesa, Usuario cliente, EstadoReserva estadoReserva);
	List<Reserva> findByUsuarioId(Long usuarioId);
	*/
	
}
