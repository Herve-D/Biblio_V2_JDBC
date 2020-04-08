package biblio.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.EmpruntEnCoursDb;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateurDao;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;
import biblio.util.Ui;

public class EmprunterCtl {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		ConnectionFactory cf = new ConnectionFactory();
		Connection cnx = cf.getConnectionSansAutoCommit("oracle.jdbc.driver.OracleDriver",
				"jdbc:oracle:thin:@localhost:1521/xepdb1", "biblio", "biblio");

		Utilisateur user = null;
		Exemplaire ex = null;
		EmpruntEnCoursDb empruntDb = null;
		Integer idUser;

		UtilisateurDao userDao = new UtilisateurDao(cnx);
		ExemplaireDao exDao = new ExemplaireDao(cnx);
		EmpruntEnCoursDao empruntDao = new EmpruntEnCoursDao(cnx);

		System.out.println(userDao.findAll());
		do {
			idUser = Ui.saisieId("Entrer ID Utilisateur :");
			if (idUser == null)
				break;

			user = (Utilisateur) userDao.findByKey(idUser);
			JOptionPane.showMessageDialog(null, "Utilisateur" + user, "Confirmation", JOptionPane.INFORMATION_MESSAGE);
		} while (idUser != null);

	}

}
