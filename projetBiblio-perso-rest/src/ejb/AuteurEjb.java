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

/**
 * Session Bean implementation class AuteurEjb
 */
@Stateless(mappedName = "auteurEjb")
@LocalBean
public class AuteurEjb implements AuteurEjbLocal {
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
	public AuteurEjb() {
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
	public Auteur persistAuteur(Auteur auteur) {
		em.persist(auteur);
		return auteur;
	}

	/**
	 * @generated DT_ID=none
	 */
	public Auteur mergeAuteur(Auteur auteur) {
		return em.merge(auteur);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeAuteur(Auteur auteur) {
		auteur = em.find(Auteur.class, auteur.getNum());
		em.remove(auteur);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Auteur> getAuteurFindAll() {
		return em.createNamedQuery("Auteur.findAll").getResultList();
	}
	
	public Auteur chercherAuteur(int key) {
		String queryString = "SELECT a FROM Auteur a WHERE a.num = :n";
		TypedQuery<Auteur> query = em.createQuery(queryString,Auteur.class);
		query.setParameter("n", key);
		Auteur aut =query.getSingleResult();
		return aut;
	}
}
