package entities;

import modelo.Persona;
import modelo.Reclamo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="personas")
public class PersonaEntity {

	@Id
	private String documento;
	private String nombre;
	
	public PersonaEntity() { }
	
	public PersonaEntity(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public Persona toNegocio() {
		return new Persona(documento, nombre);
	}
}
