package remota;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerApuestas {
	

	public static void main(String[] args) {
		//Datos de la bd
		String server = "jdbc:sqlserver://localhost;databaseName=LeoTurf";
		String usuario = "prueba";
		String pass = "123";
		
		try {
			//Declaro el objeto servidor(ManejadorApuestas en mi caso)
            ManejadorApuestas objetoServidor;
            System.out.println("Creando el registro de objetos remotos");
            //Creo un registro en el puerto 50008
            Registry reg = null;
            try {
                reg = LocateRegistry.createRegistry(50008);
            } catch (RemoteException ex) {
                System.out.println("ERROR: No se ha podido crear el registro");
                Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Instancio el objeto ManejadorApuestas
            System.out.println ("Creando el objeto servidor e instanciándolo en el registro");
            objetoServidor = new ManejadorApuestas(server,usuario,pass);
            //Asigno una clave "Apostando" al objeto remoto para que sea accesible a través de la red
            reg.rebind("Apostando", (ApuestasInterface)UnicastRemoteObject.exportObject(objetoServidor,0));
            System.out.println("Exito");
            
            //Bucle infinito para que la ejecución se mantenga y
            //no se pierda el registro del objeto remoto
            while (true) {}
        } catch (RemoteException ex) {
            Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
