package com.rmt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rmt.entities.Usuario;
import com.rmt.entities.EstadoReserva;
import com.rmt.entities.Mesa;
import com.rmt.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{


	@Query("SELECT r FROM Reserva r WHERE r.cliente.id = :clienteId")
    List<Reserva> findByCliente(@Param("clienteId") Long clienteId, Pageable pageable);

	List<Reserva> findByCliente(@Param("clienteId") Long clienteId);
	/*
	boolean existsByMesaAndUsuarioAndEstadoReserva(Mesa mesa, Usuario cliente, EstadoReserva estadoReserva);
	List<Reserva> findByUsuarioId(Long usuarioId);
	*/
	
	

}
