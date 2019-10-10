package modelo;

import daos.DuenioDAO;
import daos.InquilinoDAO;
import entities.PersonaEntity;
import exceptions.PersonaException;
import views.PersonaView;

public class Persona {

	private String documento;
	private String nombre;

	public Persona(String documento, String nombre) {
		this.documento = documento;
		this.nombre = nombre;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNombre() {
		return nombre;
	}

	public PersonaView toView() {
		return new PersonaView(documento, nombre);
	}

	public void save() {
		
	}

	public void delete() {
		
	}

	public PersonaEntity toEntity() {
		return new PersonaEntity(documento, nombre);
	}

	public Unidad getUnidadInquilino() throws PersonaException {
		return InquilinoDAO.getInstancia().getUnidadByDocumento(documento);
	}

	public Unidad getUnidadDuenio() throws PersonaException {
		return DuenioDAO.getInstancia().getUnidadByDocumento(documento);
	}

}
