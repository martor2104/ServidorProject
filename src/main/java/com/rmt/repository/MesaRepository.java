package com.rmt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rmt.entities.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>{

    @Query("SELECT m FROM Mesa m WHERE m.reserva.cliente.id = :usuarioId")
    Page<Mesa> findByReservasClienteId(Long usuarioId, Pageable pageable);

	
}
