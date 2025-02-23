package mitier;

import java.util.Date;



public class LivreModel {
	private String titre;
	private String auteur;
	private Date anne_publication;

	public LivreModel() {
		this.titre=titre;
		this.auteur=auteur;
		this.anne_publication=anne_publication;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public Date getAnne_publication() {
		return anne_publication;
	}
	public void setAnne_publication(Date anne_publication) {
		this.anne_publication = anne_publication;
	}
}
