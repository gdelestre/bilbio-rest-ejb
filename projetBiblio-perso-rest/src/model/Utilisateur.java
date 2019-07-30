package model;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the utilisateurs database table.
 * 
 */
@Entity
@Table(name="utilisateurs")
@NamedQuery(name="Utilisateur.findAll", query="SELECT u FROM Utilisateur u")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int num;

	private String acces;

	@Temporal(TemporalType.DATE)
	private Date dateInscription;

	private String identifiant;

	private String mdp;
	@Transient
	private Format formatter;
	
	public String toString() {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String s = formatter.format(dateInscription);
		return identifiant+", "+acces+". Date : "+s;
	}


	//bi-directional many-to-one association to Location
	@OneToMany(mappedBy="utilisateur", fetch=FetchType.EAGER)
	private List<Location> locations;

	public Utilisateur() {
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getAcces() {
		return this.acces;
	}

	public void setAcces(String acces) {
		this.acces = acces;
	}

	public Date getDateInscription() {
		return this.dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getMdp() {
		return this.mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	@JsonbTransient
	public List<Location> getLocations() {
		return this.locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}

	public Location addLocation(Location location) {
		getLocations().add(location);
		location.setUtilisateur(this);

		return location;
	}

	public Location removeLocation(Location location) {
		getLocations().remove(location);
		location.setUtilisateur(null);

		return location;
	}
	
	public Utilisateur(String identifiant, String mdp, String acces, Date dateInscription) {
		this.identifiant = identifiant;
		this.mdp = mdp;
		this.acces = acces;
		this.dateInscription=dateInscription;
	}

}