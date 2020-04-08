package biblio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import biblio.domain.Exemplaire;
import biblio.util.EnumStatusExemplaire;

public class ExemplaireDao {

	private Connection cnx = null;

	public ExemplaireDao() {

	}

	public ExemplaireDao(Connection cnx) {
		this.cnx = cnx;
	}

	public Exemplaire findByKey(Integer id) throws SQLException {
		Exemplaire exemplaire = null;

		PreparedStatement ps = cnx.prepareStatement("select * from exemplaire where idexemplaire = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			exemplaire = new Exemplaire(rs.getInt("idexemplaire"), rs.getDate("dateachat"),
					EnumStatusExemplaire.valueOf(rs.getString("status")), rs.getString("isbn"));
		}
		rs.close();
		ps.close();

		return exemplaire;
	}

	public List<Exemplaire> findAll() throws SQLException {
		List<Exemplaire> listeExemplaire = new ArrayList<>();

		PreparedStatement ps = cnx.prepareStatement("select * from exemplaire order by idexemplaire");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			listeExemplaire.add(new Exemplaire(rs.getInt("idexemplaire"), rs.getDate("dateachat"),
					EnumStatusExemplaire.valueOf(rs.getString("status")), rs.getString("isbn")));
		}
		rs.close();
		ps.close();

		return listeExemplaire;
	}

	public Boolean updateStatus(Exemplaire exemplaire) throws SQLException {
		PreparedStatement ps = cnx.prepareStatement("update exemplaire set status = ? where idexemplaire = ?");
		ps.setString(1, exemplaire.getStatus().toString());
		ps.setInt(2, exemplaire.getIdExemplaire());
		int i = ps.executeUpdate();
		ps.close();
		return ((i > 0) ? true : false);
	}

}
