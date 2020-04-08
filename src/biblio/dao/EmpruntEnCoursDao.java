package biblio.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpruntEnCoursDao {

	private Connection cnx = null;

	public EmpruntEnCoursDao() {

	}

	public EmpruntEnCoursDao(Connection cnx) {
		this.cnx = cnx;
	}

	public EmpruntEnCoursDb findByKey(Integer idExemplaire) throws SQLException {
		EmpruntEnCoursDb emprunt = null;

		PreparedStatement ps = cnx.prepareStatement("select * from empruntencours where idexemplaire = ?");
		ps.setInt(1, idExemplaire);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			emprunt = new EmpruntEnCoursDb(rs.getDate("dateemprunt"), rs.getInt("idexemplaire"),
					rs.getInt("idutilisateur"));
		}
		rs.close();
		ps.close();

		return emprunt;
	}

	public List<EmpruntEnCoursDb> findByUtilisateur(Integer idUtilisateur) throws SQLException {
		List<EmpruntEnCoursDb> emprunts = new ArrayList<>();

		PreparedStatement ps = cnx.prepareStatement("select * from empruntencours where idutilisateur = ?");
		ps.setInt(1, idUtilisateur);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			emprunts.add(
					new EmpruntEnCoursDb(rs.getDate("date"), rs.getInt("idexemplaire"), rs.getInt("idutilisateur")));
		}
		rs.close();
		ps.close();

		return emprunts;
	}

	public boolean insertEmpruntEnCours(EmpruntEnCoursDb emprunt) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement(
				"insert into empruntencours(idexemplaire, idutilisateur, dateemprunt) values (?, ?, ?)");
		ps.setInt(1, emprunt.getIdExemplaire());
		ps.setInt(2, emprunt.getIdUtilisateur());
		ps.setDate(3, new Date(emprunt.getDateEmprunt().getTime()));
		int i = ps.executeUpdate();
		ps.close();
		return ((i > 0) ? true : false);
	}

	public boolean delete(int id) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("delete from empruntencours where idexemplaire = ?");
		ps.setInt(1, id);
		int i = ps.executeUpdate();
		ps.close();
		return ((i > 0) ? true : false);
	}

}
