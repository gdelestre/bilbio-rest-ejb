package ejb;

import java.util.List;

import javax.ejb.Local;

import model.Livre;
import model.Location;
import model.Utilisateur;

@Local
public interface LocationEjbLocal {
	public Location persistLocation(Location location);
	public void removeLocation(Location location);
	public Location mergeLocation(Location location);
	public List<Location> getLocationUtilisateurEnCours(Utilisateur ut);
	public List<Location> getLocationEnCours();
	public List<Location> getAllLocationUtilisateur(Utilisateur ut);
	public List<Location> getAllLocationLivre(Livre liv);
	public Location chercherLocation(int key);
}
