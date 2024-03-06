package com.rmt.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rmt.entities.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long>{

    @Query("SELECT m FROM Mesa m WHERE m.reserva.id = :reservaId")
    List<Mesa> findMesaByReserva(Long reservaId);

	
}
