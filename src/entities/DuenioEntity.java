package entities;

import javax.persistence.*;

@Entity
@Table(name="duenios")
public class DuenioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_duenio")
	private Integer id;
	@ManyToOne
	@JoinColumn(name="id_unidad")
	private UnidadEntity unidad;
	@ManyToOne
	@JoinColumn(name="documento")	
	private PersonaEntity persona;
	
	public DuenioEntity() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UnidadEntity getUnidad() {
		return unidad;
	}

	public void setUnidad(UnidadEntity unidad) {
		this.unidad = unidad;
	}

	public PersonaEntity getPersona() {
		return persona;
	}

	public void setPersona(PersonaEntity persona) {
		this.persona = persona;
	}
	
}
