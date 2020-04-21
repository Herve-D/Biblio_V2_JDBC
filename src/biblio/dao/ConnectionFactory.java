package biblio.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

	public ConnectionFactory() {
	}

	public Connection getConnection(String chemin) {

		Properties file = new Properties();

		try {
			file.load(new FileInputStream(chemin));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String nomPilote = file.getProperty("driver");
		String URLBD = file.getProperty("url");
		String authorizationID = file.getProperty("user");
		String password = file.getProperty("pwd");

		Connection cnx = null;

		try {
			Class.forName(nomPilote);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			cnx = DriverManager.getConnection(URLBD, authorizationID, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnx;
	}

	public Connection getConnectionSansAutoCommit(String chemin) {

		Properties file = new Properties();

		try {
			file.load(new FileInputStream(chemin));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String nomPilote = file.getProperty("driver");
		String URLBD = file.getProperty("url");
		String authorizationID = file.getProperty("user");
		String password = file.getProperty("pwd");

		Connection cnx = null;

		try {
			Class.forName(nomPilote);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			cnx = DriverManager.getConnection(URLBD, authorizationID, password);
			cnx.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cnx;
	}

}
