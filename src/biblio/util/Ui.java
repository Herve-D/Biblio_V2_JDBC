package biblio.util;

import javax.swing.JOptionPane;

public class Ui {

	public static Integer saisieId(String label) {
		String chaine;
		Integer id = null;
		do {
			chaine = JOptionPane.showInputDialog(label);
			if (chaine == null)
				break;

			try {
				id = Integer.parseInt(chaine);
				break;
			} catch (Exception e) {
				// e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Entrez un nombre valide");
			}

		} while (chaine != null);
		return id;
	}

	public static String saisie(String label) {
		String chaine = JOptionPane.showInputDialog(label);
		return chaine;
	}

}
