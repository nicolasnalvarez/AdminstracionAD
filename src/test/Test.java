package test;

import controlador.Controlador;
import exceptions.*;
import modelo.*;
import views.EdificioView;
import views.PersonaView;
import views.ReclamoView;
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

		//Registro con persona que existe
		//Controlador.getInstancia().registrar("DNI92956906","lucas","3357");

		//Login

		//Controlador.getInstancia().login("pEPITO","123456");
		//Login Correcto
		Controlador.getInstancia().login("lucas","3357");

		//Login Incorrecto
		Controlador.getInstancia().login("lucas","11111111");

		//Registro con persona que no existe
		//Controlador.getInstancia().registrar("sadsdsasdsd22","pEPITO","123456");



	}

}
