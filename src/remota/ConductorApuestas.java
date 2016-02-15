package remota;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import turf.Carrera;

public class ConductorApuestas {
	private final static String HOST = "127.0.0.1";
	private final static int PORT = 9009;
	private final static String OBJECT_NAME = "Apostando";
	private final static String DB = "localhost;databaseName=LeoTurf";
	private final static String USER = "prueba";
	private final static String PASS = "123";

	public static void main(String[] args) {
		ApuestasInterface server = null;
		ArrayList<Carrera> listaCarreras = new ArrayList<>();
		//Instancio el objeto remoto LeoPOP3
		try {
			Registry registry = LocateRegistry.getRegistry(HOST, PORT);
			server = (ApuestasInterface) registry.lookup(OBJECT_NAME);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		try {
			ManejadorApuestas maneja = new ManejadorApuestas(DB, USER, PASS);
			//Obtengo las carreras existentes
			listaCarreras = server.obtenerCarreras();
			for(Carrera race:listaCarreras) {
				System.out.println(race.toString());
			}
			
			
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
