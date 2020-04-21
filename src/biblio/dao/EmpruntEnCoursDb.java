package biblio.dao;

import java.util.Date;

import biblio.domain.EmpruntEnCours;
import biblio.domain.Exemplaire;
import biblio.domain.Utilisateur;

public class EmpruntEnCoursDb extends EmpruntEnCours {

	private Integer idExemplaire;
	private Integer idUtilisateur;

	public EmpruntEnCoursDb() {

	}

	public EmpruntEnCoursDb(Date dateEmprunt, Utilisateur utilisateur, Exemplaire exemplaire, Integer idExemplaire,
			Integer idUtilisateur) {
		super(dateEmprunt, utilisateur, exemplaire);
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}

	public EmpruntEnCoursDb(Date dateEmprunt, Integer idExemplaire, Integer idUtilisateur) {
		super(dateEmprunt);
		this.idExemplaire = idExemplaire;
		this.idUtilisateur = idUtilisateur;
	}

	public Integer getIdExemplaire() {
		return idExemplaire;
	}

	public void setIdExemplaire(Integer idExemplaire) {
		this.idExemplaire = idExemplaire;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	@Override
	public String toString() {
		return super.toString() + "\nEmpruntEnCoursDb [ ID Exemplaire : " + idExemplaire + ", ID Utilisateur : "
				+ idUtilisateur + " ]";
	}

}
