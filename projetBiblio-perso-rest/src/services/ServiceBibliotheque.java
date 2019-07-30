package services;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.util.List;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ejb.AuteurEjbLocal;
import ejb.LivreEjbLocal;
import ejb.LocationEjbLocal;
import ejb.RangementEjbLocal;
import ejb.UtilisateurEjbLocal;
import model.Auteur;
import model.Livre;
import model.Location;
import model.Rangement;
import model.Utilisateur;

@SessionScoped
@Path("services")
public class ServiceBibliotheque implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private AuteurEjbLocal ejbAuteur;
	@EJB
	private LivreEjbLocal ejbLivre;
	@EJB
	private UtilisateurEjbLocal ejbUtilisateur;
	@EJB
	private RangementEjbLocal ejbRangement;
	@EJB
	private LocationEjbLocal ejbLocation;
	
	final String etatDisponible ="disponible";
	final String etatEmprunte ="emprunte";
	final String etatQRCodeAGenerer = "QRCode à générer";
	
//----------------------------METHODE GET----------------------------
	//#######################AUTEUR#######################
	@Path("auteurs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Auteur> voirLesAuteurs() {
		return ejbAuteur.getAuteurFindAll();
	}

	@Path("auteurs/{numauteur}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Auteur voirUnAuteur(@PathParam("numauteur") String numAuteur) {
		int num = Integer.valueOf(numAuteur);
		Auteur a = ejbAuteur.chercherAuteur(num);
		if (a != null)
			return a;
		else
			return null;
	}
	
	//#######################LIVRE#######################
	@Path("livres")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livre> voirLesLivres() {
		List<Livre> listeLivre = ejbLivre.getLivreFindAll();
		return listeLivre;
	}

	@Path("livres/{numlivre}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Livre voirUnLivre(@PathParam("numlivre") String numLivre) {
		int num = Integer.valueOf(numLivre);
		Livre l = ejbLivre.chercherLivre(num);
		if (l != null)
			return l;
		else
			return null;
	}
	
	@Path("livres/{genre}/{etat}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livre> voirLesLivresParGenre(@PathParam("genre") String genre, @PathParam("etat") String etat ) {
		return ejbLivre.getLivreGenre(genre, etat);
	}

	@Path("livres/auteurs/{numauteur}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livre> voirLesLivresParAuteur(@PathParam("numauteur") String numAuteur) {
		int num = Integer.valueOf(numAuteur);
		Auteur aut = ejbAuteur.chercherAuteur(num);
		return ejbLivre.getLivreAuteur(aut);
	}
	
	@Path("livres/rangements/{numrangement}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livre> voirLesLivresParRangement(@PathParam("numrangement") String numRangement) {
		int num = Integer.valueOf(numRangement);
		Rangement rgt = ejbRangement.chercherRangement(num);
		return ejbLivre.getLivreRangement(rgt);
	}
	@Path("livres/rangements/null")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livre> getLesLivresRangementNull() {
		return ejbLivre.getLivreRangementNull();
	}
	
	
	//#######################UTILISATEUR#######################
	@Path("utilisateurs")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Utilisateur> voirLesUtilisateurs() {
		List<Utilisateur> listeUtilisateur = ejbUtilisateur.getUtilisateurFindAll();
		return listeUtilisateur;
	}
	
	@Path("utilisateurs/clients")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Utilisateur> voirLesClients() {
		List<Utilisateur> listeUtilisateur = ejbUtilisateur.chercheClient();
		return listeUtilisateur;
	}

	@Path("utilisateurs/{identifiant}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur voirUnUtilisateur(@PathParam("identifiant") String id) {
		Utilisateur u = ejbUtilisateur.chercheUtilisateur(id);
		if (u != null)
			return u;
		else
			return null;
	}
	
	//#######################RANGEMENT#######################
	@Path("rangements/{allee}/{etagere}/{section}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Rangement voirUnRangement
	(@PathParam("allee") String allee1, @PathParam("etagere") String etagere1, @PathParam("section") String section1) {
		Rangement rgt = ejbRangement.chercherRangement(allee1, etagere1, section1);
		if (rgt != null)
			return rgt;
		else
			return null;
	}
	
	//#######################LOCATION#######################
	
	@Path("locations/{numlocation}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Location voirUneLocation(@PathParam("numlocation") String numLocation) {
		int num = Integer.valueOf(numLocation);
		Location loc = ejbLocation.chercherLocation(num);
		if (loc != null)
			return loc;
		else
			return null;
	}
	
	@Path("locations/{identifiant}/encours")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> voirLesLocationParUtilisateurEnCours(@PathParam("identifiant") String id) {
		Utilisateur ut = ejbUtilisateur.chercheUtilisateur(id);
		List<Location> liste = ejbLocation.getLocationUtilisateurEnCours(ut);
		return liste;
	}
	
	@Path("locations/utilisateurs/{identifiant}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> voirLesLocationsParUtilisateur(@PathParam("identifiant") String id) {
		Utilisateur ut = ejbUtilisateur.chercheUtilisateur(id);
		List<Location> liste = ejbLocation.getAllLocationUtilisateur(ut);
		return liste;
	}
	
	@Path("locations/encours")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> voirLesLocationsEnCours() {
		List<Location> liste = ejbLocation.getLocationEnCours();
		return liste;
	}
	
	
	@Path("locations/livres/{numlivre}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Location> voirLesLocationsDunLivre(@PathParam("numlivre") String numLivre) {
		int num = Integer.valueOf(numLivre);
		Livre liv = ejbLivre.chercherLivre(num);
		List<Location> liste = ejbLocation.getAllLocationLivre(liv);
		return liste;
	}
	
	//----------------------------METHODE POST----------------------------	

	//#######################AUTEUR#######################
	@Path("auteurs/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Auteur ajouterAuteur(@FormParam("nom") String n, @FormParam("prenom") String p,
			@FormParam("nationalite") String nat) {
		Auteur auteur = new Auteur(n, p, nat);
		return ejbAuteur.persistAuteur(auteur);
		
	}
	
	//#######################LIVRE#######################
	@Path("livres/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Livre ajouterLivre(@FormParam("titre") String titre, @FormParam("editeur") String edit, @FormParam("isbn") String isbn,
			@FormParam("annee") String a, @FormParam("auteur") String numAuteur, @FormParam("genre") String genre, 
			@FormParam("resume") String resume) {
		int num = Integer.valueOf(numAuteur);
		Auteur aut = ejbAuteur.chercherAuteur(num);
		short annee = Short.valueOf(a);	
		Livre livre = new Livre(annee, edit, genre, isbn, resume, titre, aut, etatQRCodeAGenerer);
		ejbLivre.persistLivre(livre);
		return livre;
	}
	
	
	//#######################UTILISATEUR#######################
	@Path("utilisateurs/add")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur ajouterUtilisateur(@FormParam("identifiant") String i,
			@FormParam("mdp") String m, @FormParam("acces") String a, @FormParam("dateInscription") String d) {
		Date date = Date.valueOf(d);
		Utilisateur utilisateur = new Utilisateur(i, m, a, date);
		return ejbUtilisateur.persistUtilisateur(utilisateur);
	}
	
	//#######################LOCATION#######################
		@Path("locations/add")
		@POST
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.APPLICATION_JSON)
		public void ajouterLocation(@FormParam("utilisateur") String id, @FormParam("livre") String numLivre,
				 @FormParam("dateEmprunt") String dateEmprunt, @FormParam("dateRetour") String dateRetour) {
			Utilisateur ut = ejbUtilisateur.chercheUtilisateur(id);
			int key = Integer.valueOf(numLivre);
			Livre liv = ejbLivre.chercherLivre(key);
			Date dateE = Date.valueOf(dateEmprunt);
			Date dateR = Date.valueOf(dateRetour);
			Location loc = new Location(dateE, dateR, liv, ut);
			ejbLocation.persistLocation(loc);
			
			liv.setEtat(etatEmprunte);
			ejbLivre.mergeLivre(liv);
		}
		
		
	
	//----------------------------METHODE PUT----------------------------
	
	//#######################LIVRE#######################
	@Path("livres/{numlivre}/qrcode")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Livre ajoutQRCode(@PathParam("numlivre") String n, @FormParam("texteQRCode") String t) {
		int num = Integer.valueOf(n);
		Livre l = ejbLivre.chercherLivre(num);
		l.setTexte_qrcode(t);
		l.setEtat(etatDisponible);
		ejbLivre.mergeLivre(l);
		return l;
	}
	
	@Path("livres/{numlivre}/rangement")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public void rangerUnLivre(@PathParam("numlivre") String numLivre,  @FormParam("numrangement") String numrangement) {
		int numL = Integer.valueOf(numLivre);
		Livre l = ejbLivre.chercherLivre(numL);
		int numR = Integer.valueOf(numrangement);
		Rangement rgt = ejbRangement.chercherRangement(numR);
		l.setRangement(rgt);
		ejbLivre.mergeLivre(l);
	}
	
	//#######################UTILISATEUR#######################
	@Path("utilisateurs/{identifiant}/nouveaumdp")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur modifierMotDePasse(@PathParam("identifiant") String id, @FormParam("nouveaumdp") String m) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Utilisateur ut = ejbUtilisateur.chercheUtilisateur(id);
		String mHach = hachageMdp(m);
		ut.setMdp(mHach);
		ejbUtilisateur.mergeUtilisateur(ut);
		return ut;
	}
	
	//#######################LOCATION#######################
	@Path("locations/{numlocation}/retour")
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public void validerRetourLivre(@PathParam("numlocation") String num, @FormParam("dateRetourReel") String date) {
		int numL = Integer.valueOf(num);
		Location loc = ejbLocation.chercherLocation(numL);
		Date dateR = Date.valueOf(date);
		loc.setDateRetourReel(dateR);
		ejbLocation.mergeLocation(loc);
		
		Livre liv = loc.getLivre();
		liv.setEtat(etatDisponible);
		ejbLivre.mergeLivre(liv);
	}

	
	//----------------------------METHODE DELETE----------------------------

	//#######################AUTEUR#######################
	@Path("auteurs/del/{numAuteur}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Auteur supprimerAuteur(@PathParam("numAuteur") String n) {
		int num = Integer.valueOf(n);
		Auteur a = ejbAuteur.chercherAuteur(num);
		ejbAuteur.removeAuteur(a);
		return a;
	}
	
	//#######################LIVRE#######################
	@Path("livres/del/{numLivre}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Livre supprimerLivre(@PathParam("numLivre") String n) {
		int num = Integer.valueOf(n);
		Livre l = ejbLivre.chercherLivre(num);
		ejbLivre.removeLivre(l);
		return l;
	}
	
	//#######################UTILISATEUR#######################
	@Path("utilisateurs/del/{identifiantUtilisateur}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void supprimerUtilisateur(@PathParam("identifiantUtilisateur") String id) {
		Utilisateur u = ejbUtilisateur.chercheUtilisateur(id);
		ejbUtilisateur.removeUtilisateur(u);
	}
	
	//#######################LOCATION#######################
	@Path("locations/del/{numLocation}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public void supprimerLocation(@PathParam("numLocation") String id) {
		int key = Integer.valueOf(id);
		Location loc = ejbLocation.chercherLocation(key);
		ejbLocation.removeLocation(loc);
	}
	
	public String hachageMdp(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[32];
		random.nextBytes(salt);		
		// 70000 itérations, clef sur 256 bits ou 32 octets
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 70000, 256);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		SecretKey secret = factory.generateSecret(spec);
		byte[] hash = secret.getEncoded();
		salt = spec.getSalt();
		String mdphach =  bytesToHex(hash)+bytesToHex(salt);
		return mdphach;
	}

	public static String bytesToHex(byte[] b) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuffer buffer = new StringBuffer();
		for (int j = 0; j < b.length; j++) {
			buffer.append(hexDigits[(b[j] >> 4) & 0x0f]);
			buffer.append(hexDigits[b[j] & 0x0f]);
		}
		return buffer.toString();
	}

}
