package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cuenta;
import model.bdd.BddConnection;

public class CuentaDAO {

	public CuentaDAO() {

	}

	public List<Cuenta> getCuentas() throws SQLException {
		List<Cuenta> cuentas = new ArrayList<Cuenta>();
		String _SQL_GET_ALL = "SELECT * FROM cuenta";

		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_GET_ALL);
		ResultSet rs = pstmt.executeQuery();

		// Iterar el result set para leer los datos
		while (rs.next()) {
			Cuenta c = new Cuenta();
			c.setId(rs.getInt("id"));
			c.setNombre(rs.getString("nombre"));
			c.setNumero(rs.getString("numero"));
			c.setSaldo(rs.getBigDecimal("saldo"));

			cuentas.add(c);
		}
		BddConnection.cerrar(pstmt);
		BddConnection.cerrar(rs);
		BddConnection.cerrar();

		return cuentas;
	}

	public void crear(Cuenta c) throws SQLException {
		String _SQL_INSERT = "INSERT INTO cuenta VALUES(nombre, numero, saldo) VALUES (?,?,?)";

		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_INSERT);
		pstmt.setString(1, c.getNombre());
		pstmt.setString(2, c.getNumero());
		pstmt.setBigDecimal(3, c.getSaldo());

		pstmt.executeUpdate();

		BddConnection.cerrar(pstmt);
		BddConnection.cerrar();
	}

	public void actualizar(Cuenta c) throws SQLException {
		String _SQL_UPDATE = "UPDATE cuenta SET nombre = ?, saldo = ? WHERE numero = ?";

		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_UPDATE);
		pstmt.setString(1, c.getNombre());
		pstmt.setBigDecimal(2, c.getSaldo());
		pstmt.setString(3, c.getNumero());

		pstmt.executeUpdate();

		BddConnection.cerrar(pstmt);
		BddConnection.cerrar();
	}
	
	public void eliminar(String numero) throws SQLException {
		String _SQL_DELETE = "DELETE cuenta WHERE numero = ?";
		
		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_DELETE);
		pstmt.setString(1, numero);
		
		pstmt.executeUpdate();
		
		BddConnection.cerrar(pstmt);
		BddConnection.cerrar();
	}

	public Cuenta encontrarPorNumero(String numero) throws SQLException {
		Cuenta cuenta = null;
		
		String _SQL_FIND_BY_NUMERO = "SELECT * FROM cuenta WHERE numero = ?";

		PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_FIND_BY_NUMERO);
		pstmt.setString(1, numero);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
	        cuenta = new Cuenta();
	        cuenta.setId(rs.getInt("id"));
	        cuenta.setNombre(rs.getString("nombre"));
	        cuenta.setNumero(rs.getString("numero"));
	        cuenta.setSaldo(rs.getBigDecimal("saldo"));
	    }

	    BddConnection.cerrar(rs);
	    BddConnection.cerrar(pstmt);
	    BddConnection.cerrar();

	    return cuenta;
	}
}