package ejb;

import java.util.List;

import javax.ejb.Local;

import model.Auteur;
import model.Livre;
import model.Location;
import model.Rangement;

@Local
public interface LivreEjbLocal {
	public Livre persistLivre(Livre livre);
	public Livre mergeLivre(Livre livre);
	public void removeLivre(Livre livre);
	public List<Livre> getLivreFindAll();
	public List<Livre>getLivreAuteur(Auteur a);
	public Livre chercherLivre(int key);
	public List<Livre>getLivreGenre (String genre, String etat);
	public List<Livre>getLivreRangement(Rangement rgt);
	List<Livre>getLivreRangementNull();
}
