package entities;

import modelo.Edificio;
import modelo.Unidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="unidades")
public class UnidadEntity {

	@Id
	@Column(name = "id_unidad")
	private int id;
	private String piso;
	private String numero;
	private String habitado;
	
	@ManyToOne
	@JoinColumn(name = "id_edificio")
	private EdificioEntity edificio;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="documento_duenio")
	List<DuenioEntity> duenios;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="documento_inquilino")
	List<InquilinoEntity> inquilinos;
	
	public UnidadEntity() {}
			
	public UnidadEntity(int id, String piso, String numero, EdificioEntity edificio) {
		this.id = id;
		this.piso = piso;
		this.numero = numero;
		this.habitado = "N";
		this.edificio = edificio;
	}

	public int getId() {
		return id;
	}

	public String getPiso() {
		return piso;
	}

	public String getNumero() {
		return numero;
	}

	public String getHabitado() {
		return habitado;
	}

	public EdificioEntity getEdificio() {
		return edificio;
	}
	
	public List<DuenioEntity> getDuenios(){
		return duenios;
	}

	public Unidad toNegocio() {
		return new Unidad(id, piso, numero, edificio.toNegocio());
	}
}
