package entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name="imagenes")
public class ImagenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagen")
	private Integer id;
	private String path;
	private String tipo;
	@ManyToOne
	@JoinColumn(name="id_reclamo")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private ReclamoEntity reclamo;

	public ImagenEntity() {}

	public ImagenEntity(String path, String tipo, ReclamoEntity reclamo) {
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
