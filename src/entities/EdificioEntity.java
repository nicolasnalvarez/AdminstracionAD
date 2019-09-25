package entities;

import modelo.Edificio;
import modelo.Reclamo;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="edificios")
public class EdificioEntity {
	
	@Id
	@Column(name = "id_edificio")
	private Integer id;
	private String nombre;
	private String direccion;
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="codigoEdificio")
	private List<UnidadEntity> unidades;
	
	public EdificioEntity() { }
	
	public EdificioEntity(int id, String nombre, String direccion) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Edificio toNegocio() {
		return new Edificio(id, nombre, direccion);
	}
}
