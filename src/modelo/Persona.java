package modelo;

import daos.DuenioDAO;
import daos.InquilinoDAO;
import entities.PersonaEntity;
import entities.UnidadEntity;
import exceptions.PersonaException;
import views.EdificioView;
import views.PersonaView;

import java.util.List;

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

	public List<Edificio> getEdificiosDuenio() throws PersonaException {
		return DuenioDAO.getInstancia().getEdificiosByDocumento(documento);
	}

	public List<Edificio> getEdificiosInquilino() throws PersonaException {
		return InquilinoDAO.getInstancia().getEdificiosByDocumento(documento);
	}

	public List<Unidad> getUnidadesDuenioByIdEdificio(int idEdificio) throws PersonaException {
		return DuenioDAO.getInstancia().getUnidadesByDocumentoYEdificio(documento, idEdificio);
	}

	public List<Unidad> getUnidadesInquilinoByIdEdificio(int idEdificio) throws PersonaException {
		return InquilinoDAO.getInstancia().getUnidadesByDocumentoYEdificio(documento, idEdificio);
	}
}
