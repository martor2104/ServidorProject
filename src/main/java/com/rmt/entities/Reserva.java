package com.rmt.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Table(name = "reservas")
@Entity
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Necesita fecha y hora para la cita")
	private LocalDate horario;
	
	@NotNull(message = "Necesita un número de clientes")
	private Integer numClientes;
	
	private EstadoReserva estadoReserva;
	
    @OneToMany(mappedBy = "reserva")
    private List<Mesa> mesas;
	
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;
    
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getHorario() {
		return horario;
	}

	public void setHorario(LocalDate horario) {
		this.horario = horario;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public List<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(List<Mesa> mesas) {
		this.mesas = mesas;
	}

	public Integer getNumClientes() {
		return numClientes;
	}

	public void setNumClientes(Integer numClientes) {
		this.numClientes = numClientes;
	}

	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}
	
    public boolean isDisponible() {

        return true;
    }	
	
}
