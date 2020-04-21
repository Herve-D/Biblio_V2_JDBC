package biblio.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import biblio.dao.ConnectionFactory;
import biblio.dao.EmpruntEnCoursDao;
import biblio.dao.EmpruntEnCoursDb;
import biblio.dao.ExemplaireDao;
import biblio.dao.UtilisateurDao;
import biblio.domain.Adherent;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;
import biblio.util.BiblioException;
import biblio.util.EnumStatusExemplaire;
import biblio.util.Ui;

public class EmprunterCtl {

	public static void main(String[] args) {

		ConnectionFactory cf = new ConnectionFactory();
		Connection cnx = cf.getConnectionSansAutoCommit("jdbc.properties");

		Utilisateur user = null;
		Exemplaire ex = null;
		EmpruntEnCoursDb empruntDb = null;
		Integer idUser, idEx, choix;
		List<EmpruntEnCoursDb> empruntDbs = null;
		@SuppressWarnings("unused")
		boolean empOk;

		try {
			do {

				idUser = Ui.saisieId("Entrer ID Utilisateur :");
				if (idUser == null)
					break;

				UtilisateurDao userDao = new UtilisateurDao(cnx);
				user = (Utilisateur) userDao.findByKey(idUser);
				JOptionPane.showMessageDialog(null, "Utilisateur trouvé :\n" + user, "Confirmation",
						JOptionPane.INFORMATION_MESSAGE);

				EmpruntEnCoursDao empruntDao = new EmpruntEnCoursDao(cnx);
				ExemplaireDao exDao = new ExemplaireDao(cnx);

				if (user instanceof Adherent) {
					empruntDbs = empruntDao.findByUtilisateur(idUser);
					user.setEmpruntEnCoursDb(empruntDbs);
					for (EmpruntEnCoursDb emprunt : empruntDbs) {
						ex = exDao.findByKey(emprunt.getIdExemplaire());
						emprunt.setExemplaire(ex);
					}
					JOptionPane.showMessageDialog(null, "Emprunts en cours :\n" + user.getEmpruntEnCoursDb(),
							"Emprunts", JOptionPane.INFORMATION_MESSAGE);
					try {
						if (user.isConditionsPretAcceptees()) {
							empOk = true;
						}
					} catch (BiblioException e) {
						JOptionPane.showMessageDialog(null, user + "\n" + e.getMessage(), "Impossible",
								JOptionPane.ERROR_MESSAGE);
						empOk = false;
						continue;
					}
				} else {
					empOk = true;
				}

				JOptionPane.showMessageDialog(null, user + "\nEmprunt possible.");

				do {
					try {
						if (user.isConditionsPretAcceptees()) {
							empOk = true;
						}
					} catch (BiblioException e) {
						JOptionPane.showMessageDialog(null, user + "\n" + e.getMessage(), "Impossible",
								JOptionPane.ERROR_MESSAGE);
						empOk = false;
						break;
					}

					idEx = Ui.saisieId("Entrer ID Exemplaire :");
					if (idEx == null)
						break;

					ex = exDao.findByKey(idEx);
					empruntDb = empruntDao.findByKey(idEx);

					if (empruntDb != null) {
						Utilisateur user2 = userDao.findByKey(empruntDb.getIdUtilisateur());
						JOptionPane.showMessageDialog(null,
								ex + "\nExemplaire déjà emprunté par l'utilisateur :\n" + user2, "Exemplaire",
								JOptionPane.WARNING_MESSAGE);
					} else {
						choix = Ui.saisieYN("L'exemplaire est disponible, voulez-vous l'emprunter ?\n" + ex);
						switch (choix) {
						case 0:
							break;
						case 1:
						case 2:
						case -1:
							continue;
						default:
							continue;
						}

						empruntDb = new EmpruntEnCoursDb(new Date(), user, ex, idEx, idUser);
						empruntDao.insertEmpruntEnCours(empruntDb);

						ex.setStatus(EnumStatusExemplaire.PRETE);
						exDao.updateStatus(ex);

						user.addEmpruntEnCoursDb(empruntDb);

						JOptionPane.showMessageDialog(null, "Emprunt effectué de l'exemplaire :\n" + empruntDb);
						JOptionPane.showMessageDialog(null,
								"Utilisateur et ses emprunts :\n" + user + "\n" + user.getEmpruntEnCoursDb());

						cnx.rollback();
					}

				} while (idEx != null);
			} while (idUser != null);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (cnx != null) {
					cnx.rollback();
					cnx.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
