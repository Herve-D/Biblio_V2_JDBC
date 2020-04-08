package biblio.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import biblio.dao.EmpruntEnCoursDb;
import biblio.util.BiblioException;

public class Utilisateur extends Personne {

	private Integer idUtilisateur;
	private String pwd;
	private String pseudonyme;

	protected List<EmpruntArchive> empruntArchive = new ArrayList<>();
	protected List<EmpruntEnCours> empruntEnCours = new ArrayList<>();
	protected List<EmpruntEnCoursDb> empruntEnCoursDb = new ArrayList<>();

	public Utilisateur() {
		super();
	}

	public Utilisateur(String nom, String prenom, Date dateNaissance, String sexe, Integer idUtilisateur, String pwd,
			String pseudonyme) {
		super(nom, prenom, dateNaissance, sexe);
		this.idUtilisateur = idUtilisateur;
		this.pwd = pwd;
		this.pseudonyme = pseudonyme;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPseudonyme() {
		return pseudonyme;
	}

	public void setPseudonyme(String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	public List<EmpruntArchive> getEmpruntArchive() {
		return empruntArchive;
	}

	public void setEmpruntArchive(List<EmpruntArchive> empruntArchive) {
		this.empruntArchive = empruntArchive;
	}

	public List<EmpruntEnCours> getEmpruntEnCours() {
		return empruntEnCours;
	}

	public void setEmpruntEnCours(List<EmpruntEnCours> empruntEnCours) {
		this.empruntEnCours = empruntEnCours;
	}

	public List<EmpruntEnCoursDb> getEmpruntEnCoursDb() {
		return empruntEnCoursDb;
	}

	public void setEmpruntEnCoursDb(List<EmpruntEnCoursDb> empruntEnCoursDb) {
		this.empruntEnCoursDb = empruntEnCoursDb;
	}

	public Integer nbEmpruntsEnCours() {
		return empruntEnCours.size();
	}

	public void addEmpruntArchive(EmpruntArchive emprunt) {
		empruntArchive.add(emprunt);
	}

	public void addEmpruntEnCours(EmpruntEnCours emprunt) throws BiblioException {
		empruntEnCours.add(emprunt);
	}

	public void addEmpruntEnCoursDb(EmpruntEnCoursDb emprunt) {
		empruntEnCoursDb.add(emprunt);
	}

	public Integer existEmpruntEnCours(Integer id) {
		for (int i = 0; i < empruntEnCours.size(); i++) {
			if (empruntEnCours.get(i).getExemplaire().getIdExemplaire() == id) {
				return i;
			}
		}
		return -1;
	}

	public Boolean delEmpruntEnCours(Integer id) {
		EmpruntEnCours e;
		for (Iterator<EmpruntEnCours> iterator = empruntEnCours.iterator(); iterator.hasNext();) {
			e = iterator.next();
			if (e.getExemplaire().getIdExemplaire() == id) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + "Utilisateur [idUtilisateur=" + idUtilisateur + ", pwd=" + pwd + ", pseudonyme="
				+ pseudonyme + "]";
	}

}
