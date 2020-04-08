package biblio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public ConnectionFactory() {

	}

	public Connection getConnection(String nomPilote, String URLBD, String authorizationID, String password)
			throws ClassNotFoundException, SQLException {
		nomPilote = "oracle.jdbc.driver.OracleDriver";
		URLBD = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		authorizationID = "biblio";
		password = "biblio";
		Connection cnx = null;
		Class.forName(nomPilote);
		cnx = DriverManager.getConnection(URLBD, authorizationID, password);
		return cnx;
	}

	public Connection getConnectionSansAutoCommit(String nomPilote, String URLBD, String authorizationID,
			String password) throws ClassNotFoundException, SQLException {
		nomPilote = "oracle.jdbc.driver.OracleDriver";
		URLBD = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		authorizationID = "biblio";
		password = "biblio";
		Connection cnx = null;
		Class.forName(nomPilote);
		cnx = DriverManager.getConnection(URLBD, authorizationID, password);
		cnx.setAutoCommit(false);
		return cnx;
	}

}
