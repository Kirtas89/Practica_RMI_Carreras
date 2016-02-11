package remota;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class GeneradorConexiones implements DataSource {
	
	/* getConnection(String username, String password)
	 * 
	 * Cabecera: Connection getConnection(String username, String password)
	 * Comentario: Devuelve una conexión dados un usuario y su contraseña
	 * Precondición: Nada
	 * Entrada: Dos cadenas que representan usuario y contraseña
	 * Salida: Un objeto de tipo Conexión
	 * Postcondición: Se devuelve una conexión asociada al nombre.
	 */
	public Connection getConexion(String servidor, String usuario, String password) throws SQLException {
		Connection conexion = DriverManager.getConnection(servidor, usuario, password);
		return conexion;
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection(String arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
