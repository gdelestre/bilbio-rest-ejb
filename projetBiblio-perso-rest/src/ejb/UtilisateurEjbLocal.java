package ejb;

import java.util.List;

import javax.ejb.Local;

import model.Utilisateur;

@Local
public interface UtilisateurEjbLocal {
	public Utilisateur persistUtilisateur(Utilisateur utilisateur);
	public void removeUtilisateur(Utilisateur utilisateur);
	public List<Utilisateur> getUtilisateurFindAll();
	public Utilisateur chercheUtilisateur(String id);
	public Utilisateur mergeUtilisateur(Utilisateur utilisateur);
	public List<Utilisateur> chercheClient();
}
