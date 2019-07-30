package model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the livre database table.
 * 
 */
@Entity
@NamedQuery(name="Livre.findAll", query="SELECT l FROM Livre l")
public class Livre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;

	private short annee;

	private String editeur;

	private String etat;

	private String genre;

	private String isbn;

	private String resume;

	@Lob
	private String texte_qrcode;

	private String titre;

	//bi-directional many-to-one association to Auteur
	@ManyToOne
	@JoinColumn(name="num_auteur")
	private Auteur auteur;

	//bi-directional many-to-one association to Rangement
	@ManyToOne
	@JoinColumn(name="num_rangement")
	private Rangement rangement;

	//bi-directional many-to-one association to Location
	@OneToMany(mappedBy="livre", fetch=FetchType.EAGER)
	private List<Location> locations;

	public Livre() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public short getAnnee() {
		return this.annee;
	}

	public void setAnnee(short annee) {
		this.annee = annee;
	}

	public String getEditeur() {
		return this.editeur;
	}

	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getTexte_qrcode() {
		return this.texte_qrcode;
	}

	public void setTexte_qrcode(String texte_qrcode) {
		this.texte_qrcode = texte_qrcode;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Auteur getAuteur() {
		return this.auteur;
	}

	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}

	public Rangement getRangement() {
		return this.rangement;
	}

	public void setRangement(Rangement rangement) {
		this.rangement = rangement;
	}

	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Location addLocation(Location location) {
		getLocations().add(location);
		location.setLivre(this);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setLivre(null);

		return location;
	}
	
	public String toString() {
		return titre+", Editeur : "+editeur+". "+genre;
	}

	public Livre(short annee, String editeur, String genre, String isbn, String resume,
			String titre, Auteur auteur, String etat) {
		super();
		this.annee = annee;
		this.editeur = editeur;
		this.genre = genre;
		this.isbn = isbn;
		this.resume = resume;
		this.titre = titre;
		this.auteur = auteur;
		this.etat=etat;
	}
	
	

}