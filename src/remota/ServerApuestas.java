package remota;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerApuestas {

	public static void main(String[] args) {
		try {
            ManejadorApuestas objetoServidor;
            System.out.println("Creando el registro de objetos remotos");
            Registry reg = null;
            try {
                reg = LocateRegistry.createRegistry(9009);
            } catch (RemoteException ex) {
                System.out.println("ERROR: No se ha podido crear el registro");
                Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println ("Creando el objeto servidor e instanciándolo en el registro");
            objetoServidor = new ManejadorApuestas("127.0.0.1","prueba","123");
            reg.rebind("Calculadora", (ManejadorApuestas)UnicastRemoteObject.exportObject(objetoServidor,0));
        } catch (RemoteException ex) {
            Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}
