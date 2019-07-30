package model;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;

	@Temporal(TemporalType.DATE)
	private Date dateEmprunt;

	@Temporal(TemporalType.DATE)
	private Date dateRetourMax;

	@Temporal(TemporalType.DATE)
	private Date dateRetourReel;
	
	@Transient
	private Format formatter;

	//bi-directional many-to-one association to Livre
	@ManyToOne
	@JoinColumn(name="numLivre")
	private Livre livre;

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="numUtilisateurs")
	private Utilisateur utilisateur;

	public Location() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Date getDateEmprunt() {
		return this.dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateRetourMax() {
		return this.dateRetourMax;
	}

	public void setDateRetourMax(Date dateRetourMax) {
		this.dateRetourMax = dateRetourMax;
	}

	public Date getDateRetourReel() {
		return this.dateRetourReel;
	}

	public void setDateRetourReel(Date dateRetourReel) {
		this.dateRetourReel = dateRetourReel;
	}
	
	
	public Livre getLivre() {
		return this.livre;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}
	
	
	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Location(Date dateEmprunt, Date dateRetourMax, Livre livre, Utilisateur utilisateur) {
		super();
		this.dateEmprunt = dateEmprunt;
		this.dateRetourMax = dateRetourMax;
		this.livre = livre;
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dEmp = formatter.format(dateEmprunt);
		String dRet = formatter.format(dateRetourMax);
		String s;
		if(dateRetourReel !=null) {
			String dVraiRet = formatter.format(dateRetourReel);
			s = "Rendu le : " + dVraiRet;	
		}else {
			s = "A rendre pour : " + dRet;
		}
		return utilisateur.getIdentifiant() + " a emprunté : " + livre.getTitre() + ", le : " + dEmp + ". " + s;
	}
	
	
	

}