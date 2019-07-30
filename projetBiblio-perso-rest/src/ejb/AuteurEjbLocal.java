package ejb;

import java.util.List;

import javax.ejb.Local;

import model.Auteur;

@Local
public interface AuteurEjbLocal {
	public Auteur persistAuteur(Auteur auteur);
	public void removeAuteur(Auteur auteur);
	public List<Auteur> getAuteurFindAll();
	public Auteur chercherAuteur(int key);
}
