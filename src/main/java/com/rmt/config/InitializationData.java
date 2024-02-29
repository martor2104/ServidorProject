package com.rmt.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rmt.entities.EstadoReserva;
import com.rmt.entities.Mesa;
import com.rmt.entities.Reserva;
import com.rmt.entities.Role;
import com.rmt.entities.Usuario;
import com.rmt.repository.MesaRepository;
import com.rmt.repository.ReservaRepository;
import com.rmt.repository.UserRepository;


@Profile("demo")
@Component
public class InitializationData implements CommandLineRunner{

    @Autowired
    private UserRepository usuarioRepository;
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private MesaRepository mesaRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final boolean borrarReservas = true;

	@Override
	public void run(String... args) throws Exception {
		if(borrarReservas) {
			usuarioRepository.deleteAll();
		}
		
		try {
			Usuario cliente = new Usuario();
			cliente.setEmail("prueba@gmail.com");
			cliente.setName("Roberto");
			cliente.setPassword(passwordEncoder.encode("password123"));
            cliente.getRoles().add(Role.ROLE_USER);
            cliente.setTelefono(123556789);
            
            usuarioRepository.save(cliente);
            
            Usuario cliente2 = new Usuario();
            
            cliente2.setEmail("prueba2@gmail.com");
			cliente2.setName("Robertito");
			cliente2.setPassword(passwordEncoder.encode("password123"));
            cliente2.getRoles().add(Role.ROLE_ADMIN);
            cliente2.setTelefono(123556789);
            
            usuarioRepository.save(cliente2);
            
            Mesa mesa1 = new Mesa();
            
            mesa1.setNumMesa(1);
            mesa1.setZona("Terraza");
            mesa1.setMaxCliente(2);
            
            mesaRepository.save(mesa1);
            
            Mesa mesa2 = new Mesa();
            
            mesa2.setNumMesa(2);
            mesa2.setZona("Terraza");
            mesa2.setMaxCliente(4);
            
            mesaRepository.save(mesa2);
            
            Reserva reserva1 = new Reserva();
            
            List<Mesa> mesas = new ArrayList<>();
            mesas.add(mesa1);
            
            reserva1.setCliente(cliente);
            reserva1.setMesas(mesas);
            reserva1.setEstadoReserva(EstadoReserva.CONFIRMADA);
            reserva1.setNumClientes(2);
            reserva1.setHorario(LocalDate.of(2024, 4, 21));
            
            reservaRepository.save(reserva1);
            
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}    
    
}
