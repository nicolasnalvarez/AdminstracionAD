package controlador;

import daos.*;
import modelo.*;
import views.EdificioView;
import views.PersonaView;
import views.ReclamoView;
import views.UnidadView;
import exceptions.*;

import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
	public List<PersonaView> inquilinosPorUnidad(int codigo, String piso, String numero) throws UnidadException, EdificioException, PersonaException{
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

	public int generarReclamo(Reclamo reclamo, List<Imagen> imagenes) {
		return reclamo.save(imagenes);
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

	/** OK */
	private Persona buscarInquilino(String documento) throws PersonaException {
		return InquilinoDAO.getInstancia().findByID(documento);
	}

	/** OK */
	private Persona buscarDuenio(String documento) throws PersonaException {
		return DuenioDAO.getInstancia().findByID(documento);
	}

	/**
	 * Registro de usuario
	 *
	 */
	// compara contra la tabla de personas para ver si existe ahi, de existir permitir el registro.
	public void registrar(String dni, String nombre, String password) throws PersonaException {

		try {
			// SI ES INQUILINO TIPO USUARIO ---> 1
			Persona nuevoUsuario = buscarInquilino(dni);
			Usuario usuario = new Usuario(nombre, password, 1);
			UsuarioDAO.getInstancia().save(usuario);

		}catch (PersonaException pex){
			// SI ES DUENIO TIPO USUARIO ---> 2
			Persona nuevoUsuario = buscarDuenio(dni);
			Usuario usuario = new Usuario(nombre, password, 2);
			UsuarioDAO.getInstancia().save(usuario);

		}
		/*
		Persona nuevoUsuario = buscarInquilino(dni);
		System.out.println(nuevoUsuario.getDocumento());
		if (nuevoUsuario == null) {
			nuevoUsuario = buscarDuenio(dni);
			if (nuevoUsuario == null) {
				System.out.println("No se puede registrr ese dni en el sistema");
			} else {
				Usuario usuario = new Usuario(nombre, password, 2);
				UsuarioDAO.getInstancia().save(usuario);
			}
		} else {
			Usuario usuario = new Usuario(nombre, password, 1);
			UsuarioDAO.getInstancia().save(usuario);

		}*/
		/*
		System.out.println(nuevoUsuario.getNombre());
		if (nuevoUsuario != null){
			Usuario usuario = new Usuario(nombre,password);
			UsuarioDAO.getInstancia().save(usuario);
		}*/


	}

	/**
	 * Verificacion de login si existe
	 * */
	public boolean login(String nombre, String password) throws LoginException, CambioPasswordException, UsuarioException{
		Usuario usuario = UsuarioDAO.getInstancia().getUsuarioByNombre(nombre);
		if(usuario.getPassword().equals(password)){
			/*Ver si le metemos esta logica para agregar expiracion de la pass"
			if(usuario.debeCambiar()) {
				throw new CambioPasswordException("La password esta vencida, debe cambiarla");
			}*/
			return true;
		}
		else{
			throw new LoginException("Los datos ingresado no son corrector, reingrese");
		}
	}
	/**
	 * Cambio de password
	 * */
	public void cambioPassword(String nombre, String password) throws CambioPasswordException, UsuarioException{
		Usuario usuario = UsuarioDAO.getInstancia().getUsuarioByNombre(nombre);
		usuario.actualizoPassword(password);
	}




}
