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

import model.Auteur;
import model.Livre;
import model.Rangement;

/**
 * Session Bean implementation class LivreEjb
 */
@Stateless(mappedName = "livreEjb")
@LocalBean
public class LivreEjb implements LivreEjbLocal {
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
	public LivreEjb() {
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
	public Livre persistLivre(Livre livre) {
		em.persist(livre);
		return livre;
	}

	/**
	 * @generated DT_ID=none
	 */
	public Livre mergeLivre(Livre livre) {
		return em.merge(livre);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeLivre(Livre livre) {
		livre = em.find(Livre.class, livre.getNum());
		em.remove(livre);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Livre> getLivreFindAll() {
		return em.createNamedQuery("Livre.findAll").getResultList();
	}
	
	public List<Livre>getLivreAuteur(Auteur aut){
		String requete = "SELECT l FROM Livre l WHERE l.auteur=:a" ;
		Query query = em.createQuery(requete);
		query.setParameter("a", aut);
		List<Livre> liste = query.getResultList() ;
		return liste;	
	}
	
	public Livre chercherLivre(int key) {
		String queryString = "SELECT l FROM Livre l WHERE l.num = :n";
		TypedQuery<Livre> query = em.createQuery(queryString,Livre.class);
		query.setParameter("n", key);
		Livre liv =query.getSingleResult();
		return liv;
	}
	
	public List<Livre>getLivreGenre (String genre, String etat){
		String requete = "SELECT l FROM Livre l WHERE l.genre=:g and l.etat=:e" ;
		Query query = em.createQuery(requete);
		query.setParameter("g", genre);
		query.setParameter("e", etat);	
		List<Livre> liste = query.getResultList() ;
		return liste;	
	}
	
	public List<Livre>getLivreRangement(Rangement rgt){
		String requete = "SELECT l FROM Livre l WHERE " ;
		Query query;	
		if (rgt!=null) {
			requete += "l.rangement=:r";
			query= em.createQuery(requete);
			query.setParameter("r", rgt);
		}
		else {
			requete += "l.rangement is null";
			   query=  em.createQuery(requete);
		}
		List<Livre> liste = query.getResultList() ;
		return liste;
	}
	
	public List<Livre>getLivreRangementNull(){
		String requete = "SELECT l FROM Livre l WHERE l.rangement is null" ;
		Query query = em.createQuery(requete);
		List<Livre> liste = query.getResultList() ;
		return liste;
	}
	
}
