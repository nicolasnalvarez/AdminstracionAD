package views;

import modelo.Reclamo;

public class MultimediaView {

	private int id;
	private String path;
	private String tipo;
	private Reclamo reclamo;

	public MultimediaView() {}

	public MultimediaView(int id, String path, String tipo, Reclamo reclamo) {
		this.id = id;
		this.path = path;
		this.tipo = tipo;
		this.reclamo = reclamo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
}
