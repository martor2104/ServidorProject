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
	
	private final MesaService mesaService;
	
	public MesaController(MesaService mesaService) {
		this.mesaService = mesaService;
	}

	
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
    
    
    
    
}
