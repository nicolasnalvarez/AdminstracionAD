package modelo;

import entities.ImagenEntity;
import views.ImagenView;

public class Imagen {

	private Integer id;
	private String path;
	private String tipo;
	private Reclamo reclamo;

	public Imagen(String path, String tipo, Reclamo reclamo) {
		this.path = path;
		this.tipo = tipo;
		this.reclamo = reclamo;
	}

	public Imagen(String path, String tipo) {
		this.path = path;
		this.tipo = tipo;
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

	public ImagenView toView() {
		return new ImagenView(id, path, tipo, reclamo);
	}

	public ImagenEntity toEntity() {
		return new ImagenEntity(path, tipo, reclamo.toEntity());
	}

}
