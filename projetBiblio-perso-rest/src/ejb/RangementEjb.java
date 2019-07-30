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

import model.Rangement;

/**
 * Session Bean implementation class RangementEjb
 */
@Stateless(mappedName = "rangementEjb")
@LocalBean
public class RangementEjb implements RangementEjbLocal {
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
	public RangementEjb() {
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
	public Rangement persistRangement(Rangement rangement) {
		em.persist(rangement);
		return rangement;
	}

	/**
	 * @generated DT_ID=none
	 */
	public Rangement mergeRangement(Rangement rangement) {
		return em.merge(rangement);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeRangement(Rangement rangement) {
		rangement = em.find(Rangement.class, rangement.getNum());
		em.remove(rangement);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Rangement> getRangementFindAll() {
		return em.createNamedQuery("Rangement.findAll").getResultList();
	}
	
	public Rangement chercherRangement(String allee, String etagere, String section) {
		String queryString = "SELECT r FROM Rangement r WHERE r.allee =:a and r.etagere=:e and r.section=:s";
		TypedQuery<Rangement> query = em.createQuery(queryString,Rangement.class);
		query.setParameter("a", allee);
		query.setParameter("e", etagere);
		query.setParameter("s", section);
		Rangement rgt =query.getSingleResult();
		return rgt;
	}
	
	public Rangement chercherRangement(int key) {
		String queryString = "SELECT r FROM Rangement r WHERE r.num = :n";
		TypedQuery<Rangement> query = em.createQuery(queryString,Rangement.class);
		query.setParameter("n", key);
		Rangement rgt =query.getSingleResult();
		return rgt;
	}
}
