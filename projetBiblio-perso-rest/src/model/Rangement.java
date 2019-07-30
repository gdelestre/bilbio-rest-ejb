package model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rangement database table.
 * 
 */
@Entity
@NamedQuery(name="Rangement.findAll", query="SELECT r FROM Rangement r")
public class Rangement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;

	private String allee;

	private String etagere;

	private String section;

	//bi-directional many-to-one association to Livre
	@OneToMany(mappedBy="rangement", fetch=FetchType.EAGER)
	private List<Livre> livres;

	public Rangement() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAllee() {
		return this.allee;
	}

	public void setAllee(String allee) {
		this.allee = allee;
	}

	public String getEtagere() {
		return this.etagere;
	}

	public void setEtagere(String etagere) {
		this.etagere = etagere;
	}

	public String getSection() {
		return this.section;
	}

	public void setSection(String section) {
		this.section = section;
	}
	
	@JsonbTransient
	public List<Livre> getLivres() {
		return this.livres;
	}

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}

	public Livre addLivre(Livre livre) {
		getLivres().add(livre);
		livre.setRangement(this);

		return livre;
	}

	public Livre removeLivre(Livre livre) {
		getLivres().remove(livre);
		livre.setRangement(null);

		return livre;
	}
	
	public String toString() {
		return livres.toString();
	}

}