package test;

import controlador.Controlador;
import exceptions.*;
import modelo.Edificio;
import modelo.Persona;
import modelo.Unidad;
import request.ImagenRequest;
import request.ReclamoRequest;
import views.EdificioView;
import views.PersonaView;
import views.UnidadView;

import java.util.Collections;
import java.util.List;

public class Test {

	public static void main(String[] args) throws EdificioException, UnidadException, PersonaException, ReclamoException, LoginException, UsuarioException, CambioPasswordException {
		
		List<EdificioView> edificios = Controlador.getInstancia().getEdificios();
		System.out.println("Edificios " + edificios.size());
		
		List<UnidadView> unidades = Controlador.getInstancia().getUnidadesPorEdificio(1);
		System.out.println("\nUnidades por edificio " + unidades.size());
		
		List<PersonaView> p1 = Controlador.getInstancia().habitantesPorEdificio(1);
		System.out.println("\nHabitantes por Edificio " +  p1.size());
		
		List<PersonaView> p2 = Controlador.getInstancia().dueniosPorEdificio(1);
		System.out.println("\nDuenios por Edificio " +  p2.size());
				
		List<PersonaView> p3 = Controlador.getInstancia().inquilinosPorEdificio(1);		
		System.out.println("\nInquilinos por Edificio " +  p3.size());

		List<PersonaView> p4 = Controlador.getInstancia().habilitadosPorEdificio(1);		
		System.out.println("\nHabilitados por Edificio " +  p4.size());
		
		List<PersonaView> pu = Controlador.getInstancia().dueniosPorUnidad(1, "1", "1");
		System.out.println("\nDuenios por unidad " + pu.size());

		List<PersonaView> iu = Controlador.getInstancia().inquilinosPorUnidad(1, "1", "1");
		System.out.println("\nInquilinos por unidad " + iu.size());

		//Registro con persona que existe (Inquilino)
		//Controlador.getInstancia().registrar("DNI31064776","lucas","3357");
		//Controlador.getInstancia().registrar("DNI31064775","lucas","3357");

		//Registro con persona que existe (Dueño)
		//Controlador.getInstancia().registrar("DNI31046277","BBB","12121");

		//Login
		System.out.println(Controlador.getInstancia().login("BBB","12121"));
		//Controlador.getInstancia().login("pEPITO","123456");
		//Login Correcto
		//Controlador.getInstancia().login("lucas","3357");

		//Login Incorrecto
		//Controlador.getInstancia().login("lucas","11111111");

		//Registro con persona que no existe
		//Controlador.getInstancia().registrar("sadsdsasdsd22","pEPITO","123456");

		Persona persona = new Persona("DNI30979256", "Carlos");
		Edificio edificio = new Edificio(1, "nicolas", "calle falsa 123");
;		Unidad unidad = new Unidad(3, "", "5", edificio);
		ReclamoRequest reclamoTest = new ReclamoRequest(persona.getDocumento(), edificio.getId(), unidad.getId(), "Lobby", "Se rompio la pared");
		ImagenRequest imagen = new ImagenRequest("test-path", "imagen");
		Controlador.getInstancia().generarReclamo(reclamoTest, Collections.singletonList(imagen));
		System.out.println("\nSe guardo el reclamo correctamente");

		List<EdificioView> edificiosPorDNI = Controlador.getInstancia().getEdificiosByDocumentoDuenio("DNI31617676");
		System.out.println("\nEdificios por DNI " + edificiosPorDNI.size());

		List<UnidadView> unidadesPorDNIDuenioYEdificio = Controlador.getInstancia().getUnidadesByDocumentoDuenioYIdEdificio("DNI31617676", 3);
		System.out.println("\nUnidades por DNI Duenio " + unidadesPorDNIDuenioYEdificio.size());

		List<UnidadView> unidadesPorDNIInquilinoYEdificio = Controlador.getInstancia().getUnidadesByDocumentoInquilinoYIdEdificio("DNI31617676", 2);
		System.out.println("\nEdificios por DNI inquilino " + unidadesPorDNIInquilinoYEdificio.size());
	}

}
