package remota;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import turf.Apuesta;
import turf.Caballo;
import turf.Carrera;

public interface ApuestasInterface extends Remote {
	public int insertaApuesta (Apuesta a) throws RemoteException;
	public Apuesta grabaApuesta (int idCarrera, int idCaballo, float importe) throws RemoteException;
	public ArrayList<Caballo> obtenerParticipantes (int carrera) throws RemoteException;
	public float obtenerApuestas (int carrera, int caballo) throws RemoteException;
	public ArrayList<Carrera> obtenerCarreras () throws RemoteException;
}