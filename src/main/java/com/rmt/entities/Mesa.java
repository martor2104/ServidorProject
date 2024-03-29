package com.rmt.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "mesas")
public class Mesa {
	
	

	public Mesa(Long id, @NotNull(message = "Tiene que haber un número maximo de personas por mesa") Integer maxCliente,
			@NotNull(message = "Tiene que haber un número para la mesa") Integer numMesa,
			@NotBlank(message = "Tiene que especificar la zona de la mesa") String zona, Reserva reserva) {
		super();
		this.id = id;
		this.maxCliente = maxCliente;
		this.numMesa = numMesa;
		this.zona = zona;
		this.reserva = reserva;
	}
	
	public Mesa() {
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Tiene que haber un número maximo de personas por mesa")
	private Integer maxCliente;
	
	@NotNull(message = "Tiene que haber un número para la mesa")
	private Integer numMesa;
	
	@NotBlank(message = "Tiene que especificar la zona de la mesa")
	private String zona;
	
    @ManyToOne
    @JoinColumn(name = "reserva_id") // nombre de la columna en la tabla mesa que hace referencia a reserva
    private Reserva reserva;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMaxCliente() {
		return maxCliente;
	}

	public void setMaxCliente(Integer maxCliente) {
		this.maxCliente = maxCliente;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Integer getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	
	
}
