package entities;

import javax.persistence.*;

@Entity
@Table(name="duenios")
public class MultimediaEntity {

	@Id
	@Column(name = "id_multimedia")
	private Integer id;
	private String path;
	private String tipo;
	@ManyToOne
	@JoinColumn(name="id_reclamo")
	private ReclamoEntity reclamo;

	public MultimediaEntity() {}

	public MultimediaEntity(String path, String tipo, ReclamoEntity reclamo) {
		this.path = path;
		this.tipo = tipo;
		this.reclamo = reclamo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ReclamoEntity getReclamo() {
		return reclamo;
	}

	public void setReclamo(ReclamoEntity reclamo) {
		this.reclamo = reclamo;
	}
}
