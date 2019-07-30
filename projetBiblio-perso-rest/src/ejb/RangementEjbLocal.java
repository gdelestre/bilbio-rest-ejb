package ejb;

import javax.ejb.Local;

import model.Rangement;

@Local
public interface RangementEjbLocal {
	public Rangement chercherRangement(String allee, String etagere, String section);
	public Rangement chercherRangement(int key);
}
