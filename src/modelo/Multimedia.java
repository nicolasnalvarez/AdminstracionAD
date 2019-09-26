package modelo;

import entities.MultimediaEntity;
import views.MultimediaView;

public class Multimedia {

	private Integer id;
	private String path;
	private String tipo;
	private Reclamo reclamo;

	public Multimedia(Integer id, String path, String tipo, Reclamo reclamo) {
		this.id = id;
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

	public Reclamo getReclamo() {
		return reclamo;
	}

	public void setReclamo(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	public MultimediaView toView() {
		return new MultimediaView(id, path, tipo, reclamo);
	}

	public MultimediaEntity toEntity() {
		return new MultimediaEntity(path, tipo, reclamo.toEntity());
	}

}
