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

import model.Utilisateur;

/**
 * Session Bean implementation class UtilisateurEjb
 */
@Stateless(mappedName = "utilisateurEjb")
@LocalBean
public class UtilisateurEjb implements UtilisateurEjbLocal {
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
	public UtilisateurEjb() {
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
	public Utilisateur persistUtilisateur(Utilisateur utilisateur) {
		em.persist(utilisateur);
		return utilisateur;
	}

	/**
	 * @generated DT_ID=none
	 */
	public Utilisateur mergeUtilisateur(Utilisateur utilisateur) {
		return em.merge(utilisateur);
	}

	/**
	 * @generated DT_ID=none
	 */
	public void removeUtilisateur(Utilisateur utilisateur) {
		utilisateur = em.find(Utilisateur.class, utilisateur.getNum());
		em.remove(utilisateur);
	}

	/**
	 * @generated DT_ID=none
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Utilisateur> getUtilisateurFindAll() {
		return em.createNamedQuery("Utilisateur.findAll").getResultList();
	}
	
	public Utilisateur chercheUtilisateur(String id) {
		String queryString = "SELECT u FROM Utilisateur u WHERE u.identifiant = :i";
		Query query = em.createQuery(queryString);
		query.setParameter("i", id);
		Utilisateur ut =(Utilisateur) query.getSingleResult();
		return ut;	
	}
	
	public List<Utilisateur> chercheClient() {
		String queryString = "SELECT u FROM Utilisateur u WHERE u.acces='client'";
		return em.createQuery(queryString).getResultList();
	}
	
	
}