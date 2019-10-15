package controlador;

import daos.EdificioDAO;
import daos.PersonaDAO;
import daos.ReclamoDAO;
import daos.UnidadDAO;
import exceptions.EdificioException;
import exceptions.PersonaException;
import exceptions.ReclamoException;
import exceptions.UnidadException;
import modelo.*;
import views.EdificioView;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Controlador {

	private static Controlador instancia;
	
	private Controlador() { }
	
	public static Controlador getInstancia() {
		if(instancia == null)
			instancia = new Controlador();
		return instancia;
	}
	/** OK */
	public List<EdificioView> getEdificios() throws EdificioException, UnidadException{
		List<EdificioView> resultado = new ArrayList<>();
		List<Edificio> edificios = EdificioDAO.getInstancia().getAll();
		for(Edificio edificio : edificios)
			resultado.add(edificio.toView());
		return resultado;
	}
	/** OK */
	public List<UnidadView> getUnidadesPorEdificio(int codigo) throws EdificioException, UnidadException{
		List<UnidadView> resultado = new ArrayList<>();
		Edificio edificio = buscarEdificio(codigo);
		List<Unidad> unidades = edificio.getUnidades();
		for(Unidad unidad : unidades)
			resultado.add(unidad.toView());
		return resultado;
	}
	
	/** OK */
	public List<PersonaView> habilitadosPorEdificio(int codigo) throws EdificioException, UnidadException, PersonaException{
		List<PersonaView> resultado = new ArrayList<>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habilitados = edificio.habilitados();
		for(Persona persona : habilitados)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> dueniosPorEdificio(int codigo) throws EdificioException, UnidadException, PersonaException{
		List<PersonaView> resultado = new ArrayList<>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> duenios = edificio.duenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}
	
	/** OK */
	public List<PersonaView> inquilinosPorEdificio(int codigo) throws EdificioException, UnidadException, PersonaException {
		List<PersonaView> resultado = new ArrayList<>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> inquilinos = edificio.inquilinos();
		for(Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> habitantesPorEdificio(int codigo) throws EdificioException, UnidadException, PersonaException{
		List<PersonaView> resultado = new ArrayList<>();
		Edificio edificio = buscarEdificio(codigo);
		Set<Persona> habitantes = edificio.habitantes();
		for(Persona persona : habitantes)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> dueniosPorUnidad(int codigo, String piso, String numero) throws UnidadException, EdificioException, PersonaException{
		List<PersonaView> resultado = new ArrayList<>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> duenios = unidad.getDuenios();
		for(Persona persona : duenios)
			resultado.add(persona.toView());
		return resultado;
	}

	/** OK */
	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException, EdificioException, PersonaException {
		List<PersonaView> resultado = new ArrayList<>();
		Unidad unidad = buscarUnidad(codigo, piso, numero);
		List<Persona> inquilinos = unidad.getInquilinos();
		for(Persona persona : inquilinos)
			resultado.add(persona.toView());
		return resultado;
	}

	public UnidadView validarInquilino(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		return persona.getUnidadInquilino().toView();
	}

	public UnidadView validarDuenio(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		return persona.getUnidadDuenio().toView();
	}

	public List<ReclamoView> todosLosReclamos() throws ReclamoException {
		List<ReclamoView> resultado = new ArrayList<>();
		List<Reclamo> reclamos = ReclamoDAO.getInstancia().getAll();
		for(Reclamo reclamo : reclamos)
			resultado.add(reclamo.toView());
		return resultado;
	}

	public ReclamoView getReclamo(int idReclamo) {
		return ReclamoDAO.getInstancia().getById(idReclamo).toView();
	}

	public int generarReclamo(Reclamo reclamo, List<Imagen> imagenes) {
		return reclamo.save(imagenes);
	}

	public List<EdificioView> getEdificiosByDocumentoDuenio(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		return persona.getEdificiosDuenio().stream().map(Edificio::toView).collect(Collectors.toList());
	}

	public List<EdificioView> getEdificiosByDocumentoInquilino(String documento) throws PersonaException {
		Persona persona = buscarPersona(documento);
		return persona.getEdificiosInquilino().stream().map(Edificio::toView).collect(Collectors.toList());
	}

	/** OK */
	private Edificio buscarEdificio(int codigo) throws EdificioException, UnidadException {
		return EdificioDAO.getInstancia().findByID(codigo);
	}
	/** OK */
	private Unidad buscarUnidad(int codigo, String piso, String numero) throws UnidadException, EdificioException{
		return UnidadDAO.getInstancia().findById(codigo, piso, numero);
	}	
	/** OK */
	private Persona buscarPersona(String documento) throws PersonaException {
		return PersonaDAO.getInstancia().findByID(documento);	
	}
}
