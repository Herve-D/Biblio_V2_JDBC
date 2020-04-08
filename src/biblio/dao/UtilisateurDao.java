package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biblio.domain.Adherent;
import biblio.domain.Employe;
import biblio.domain.Utilisateur;
import biblio.util.EnumCategorieEmploye;

public class UtilisateurDao {

	private Connection cnx;

	public UtilisateurDao() {

	}

	public UtilisateurDao(Connection cnx) {
		this.cnx = cnx;
	}

	public Utilisateur findByKey(Integer id) throws SQLException {
		Utilisateur utilisateur = null;

		PreparedStatement ps = cnx.prepareStatement(
				"select * from utilisateur u, employe e, adherent a where u.idutilisateur = ? and u.idutilisateur = e.idutilisateur and u.idutilisateur = a.idutilisateur");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			if (rs.getString("categorieutilisateur").equals("employe")) {
				utilisateur = new Employe(rs.getString("nom"), rs.getString("prenom"), rs.getDate("datenaissance"),
						rs.getString("sexe"), rs.getInt("idutilisateur"), rs.getString("pwd"),
						rs.getString("pseudonyme"), rs.getString("codematricule"),
						EnumCategorieEmploye.valueOf(rs.getString("categorieemploye")));
			} else {
				utilisateur = new Adherent(rs.getString("nom"), rs.getString("prenom"), rs.getDate("datenaissance"),
						rs.getString("sexe"), rs.getInt("idutilisateur"), rs.getString("pwd"),
						rs.getString("pseudonyme"), rs.getString("telephone"));
			}
		}
		rs.close();
		ps.close();

		return utilisateur;
	}

	public List<Utilisateur> findAll() throws SQLException {
		List<Utilisateur> listeUtilisateur = new ArrayList<>();

		PreparedStatement ps = cnx.prepareStatement(
				"select * from utilisateur u, employe e, adherent a where u.idutilisateur = e.idutilisateur and u.idutilisateur = a.idutilisateur");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			if (rs.getString("categorieutilisateur").equals("employe")) {
				listeUtilisateur.add(new Employe(rs.getString("nom"), rs.getString("prenom"),
						rs.getDate("datenaissance"), rs.getString("sexe"), rs.getInt("idutilisateur"),
						rs.getString("pwd"), rs.getString("pseudonyme"), rs.getString("codematricule"),
						EnumCategorieEmploye.valueOf(rs.getString("categorieemploye"))));
			} else {
				listeUtilisateur.add(new Adherent(rs.getString("nom"), rs.getString("prenom"),
						rs.getDate("datenaissance"), rs.getString("sexe"), rs.getInt("idutilisateur"),
						rs.getString("pwd"), rs.getString("pseudonyme"), rs.getString("telephone")));
			}
		}
		rs.close();
		ps.close();

		return listeUtilisateur;
	}

}
