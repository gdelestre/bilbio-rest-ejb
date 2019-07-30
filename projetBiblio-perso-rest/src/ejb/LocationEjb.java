package ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Livre;
import model.Location;
import model.Utilisateur;

/**
 * Session Bean implementation class LocationEjb
 */
@Stateless(mappedName = "locationEjb")
@LocalBean
public class LocationEjb implements LocationEjbLocal {
	/**
	 * @generated DT_ID=none
	 */
	@Resource
	SessionContext sessionContext;
	/**
	 * @generated DT_ID=none
	 */
	@PersistenceContext(unitName = "projetBiblio-perso-rest")
	private EntityManager em;

	/**
	 * @generated DT_ID=none
	 */
	public LocationEjb() {
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
		Query query = em.createQuery(jpqlStmt);
		if (firstResult > 0) {
			query = query.setFirstResult(firstResult);
		}
		if (maxResults > 0) {
			query = query.setMaxResults(maxResults);
		}
		return query.getResultList();
	}

	/**
	 * @generated DT_ID=none
	 */
	public Location persistLocation(Location location) {
		em.persist(location);
		return location;
	}

	/**
	 * @generated DT_ID=none
	 */
	public Location mergeLocation(Location location) {
		return em.merge(location);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeLocation(Location location) {
		location = em.find(Location.class, location.getNum());
		em.remove(location);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Location> getLocationFindAll() {
		return em.createNamedQuery("Location.findAll").getResultList();
	}
	
	public List<Location> getLocationUtilisateurEnCours(Utilisateur ut){
		String requete = "SELECT l FROM Location l WHERE l.utilisateur=:u and l.dateRetourReel is null" ;
		Query query = em.createQuery(requete);
		query.setParameter("u", ut);
		List<Location> liste = query.getResultList();
		return liste;
	}
	
	public List<Location> getAllLocationUtilisateur(Utilisateur ut){
		String requete = "SELECT l FROM Location l WHERE l.utilisateur=:u" ;
		Query query = em.createQuery(requete);
		query.setParameter("u", ut);
		List<Location> liste = query.getResultList();
		return liste;
	}
	
	public List<Location> getAllLocationLivre(Livre liv){
		String requete = "SELECT l FROM Location l WHERE l.livre=:x" ;
		Query query = em.createQuery(requete);
		query.setParameter("x", liv);
		List<Location> liste = query.getResultList();
		return liste;
	}
	
	public List<Location> getLocationEnCours(){
		String requete = "SELECT l FROM Location l WHERE l.dateRetourReel is null" ;
		Query query = em.createQuery(requete);
		List<Location> liste = query.getResultList();
		return liste;
	}
	
	public Location chercherLocation(int key) {
		String queryString = "SELECT l FROM Location l WHERE l.num = :n";
		TypedQuery<Location> query = em.createQuery(queryString,Location.class);
		query.setParameter("n", key);
		Location loc =query.getSingleResult();
		return loc;
	}
}