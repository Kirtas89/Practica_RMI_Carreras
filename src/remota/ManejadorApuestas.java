/*
 * Da información sobre las apuestas y permite dar de alta nuevas apuestas
 */
package remota;

import java.rmi.RemoteException;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import turf.*;

/**
 *
 * @author Leo
 */
public class ManejadorApuestas implements ApuestasInterface {
    String servidor;
    String usuarioBD;
    String claveBD;
    Connection conexion;
    GeneradorConexiones conex;

    public ManejadorApuestas(String servidor, String usuarioBD, String claveBD) {
        this.servidor = servidor;
        this.usuarioBD = usuarioBD;
        this.claveBD = claveBD;
        this.conex = new GeneradorConexiones();
        try {
            conexion = conex.getConexion(servidor, usuarioBD, claveBD);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private String generaClave (Apuesta ap){
        String clave = "";
        Random r = new Random();
        int n;
        for (int i =0;i<Apuesta.LONGCLAVE;i++){
            n = r.nextInt(80)+25;
            clave += (char) n;
        }
        return clave;
    }
    /* (non-Javadoc)
	 * @see remota.ApuestasInterface#insertaApuesta(turf.Apuesta)
	 */
    @Override
	public int insertaApuesta(Apuesta a) throws RemoteException {
        int id = 0;
//        String sentencia = "EXECUTE dbo.grabaApuesta "+a.getIdCarrera()+","+a.getIdcaballo()+","+a.getImporte()+",'"+a.getClave()+"'";
        String sentencia = "EXECUTE dbo.grabaApuesta ?, ?, ?, ?, ?";
        System.out.println(sentencia);
        try {
            CallableStatement sen = conexion.prepareCall (sentencia);
            sen.setInt(1, a.getIdCarrera());
            sen.setInt(2, a.getIdcaballo());
            sen.setFloat(3, a.getImporte());
            sen.setString(4, a.getClave());
            sen.registerOutParameter(5, java.sql.Types.INTEGER);
            sen.executeUpdate();
            id = sen.getInt(5);
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    /* (non-Javadoc)
	 * @see remota.ApuestasInterface#grabaApuesta(int, int, float)
	 */
    @Override
	public Apuesta grabaApuesta (int idCarrera, int idCaballo, float importe) throws RemoteException {
        Apuesta nuevaApuesta = new Apuesta ( idCaballo, idCarrera, importe);
        String clave = generaClave (nuevaApuesta);
        nuevaApuesta.setClave(clave);
        int id = insertaApuesta (nuevaApuesta);
        nuevaApuesta.setId(id);
        return nuevaApuesta;
    }
     /*
     Recibe el ID de una carrera nos devuelve una lista de los caballos inscritos en la misma
     */
    /* (non-Javadoc)
	 * @see remota.ApuestasInterface#obtenerParticipantes(int)
	 */
    @Override
	public ArrayList<Caballo> obtenerParticipantes (int carrera) throws RemoteException {
        ArrayList<Caballo> participantes = new ArrayList();
        String sentencia = "SELECT C.Id, C.Nombre FROM LTCaballos AS C JOIN LTCaballosCarreras AS CC ON C.Id = CC.IDCaballo WHERE CC.IDCarrera = "+carrera;
        try {
             Statement sen = conexion.createStatement();
             ResultSet rs = sen.executeQuery(sentencia);
             while (rs.next()){
                 Caballo c = new Caballo(rs.getInt("Id"),rs.getString("Nombre"));
                 participantes.add(c);
             }
             rs.close();
             sen.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participantes;
    }
    /*
     Recibe el ID de una carrera y el de un caballo y nos devuelve la cantidad que hay apostada en ese momento
     */
    /* (non-Javadoc)
	 * @see remota.ApuestasInterface#obtenerApuestas(int, int)
	 */
    @Override
	public float obtenerApuestas (int carrera, int caballo) throws RemoteException {
        float total=0.0f;
        String sentencia = "SELECT ISNULL(SUM(Importe),0) FROM LTApuestas WHERE IDCarrera = "+carrera+" AND IDCaballo = "+caballo;
        try {
            Statement sen = conexion.createStatement();
            ResultSet rs = sen.executeQuery(sentencia);
            rs.next();
            total = rs.getFloat(1);
            rs.close();
            sen.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return total;
    }
    /* (non-Javadoc)
	 * @see remota.ApuestasInterface#obtenerCarreras()
	 */
    @Override
	public ArrayList<Carrera> obtenerCarreras () throws RemoteException {
        ArrayList<Carrera> carrerasAbiertas = new ArrayList();
        String sentencia ="SELECT DISTINCT ID, Hipodromo FROM LTCarreras As C JOIN LTCaballosCarreras AS CC ON C.ID = CC.IDCarrera Where Fecha > GETDATE()";
        try {
             Statement sen = conexion.createStatement();
             ResultSet rs = sen.executeQuery(sentencia);
             while (rs.next()){
                 Carrera c = new Carrera(rs.getInt("Id"),rs.getString("Hipodromo"));
                 carrerasAbiertas.add(c);
             }
             rs.close();
             sen.close();
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorApuestas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carrerasAbiertas;
    }
}
