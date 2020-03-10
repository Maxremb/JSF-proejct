package com.fr.adaming.managedBean;

import java.util.List;

import com.fr.adaming.entity.Categorie;
import com.fr.adaming.entity.Note;
import com.fr.adaming.entity.Realisateur;

public class SingleMB {
	
	private ListFilmMB listefilmMB = new ListFilmMB();
	
	protected String langue;

	protected String nom;

	protected int duree;

	protected Realisateur realisateur;

	protected List<Categorie> categories;

	protected List<Note> notes;
	
	 public ListFilmMB getListFilmMB()
	    {
	    return listefilmMB;
	    }

	    public void setListFilmMB(ListFilmMB listefilmMB)
	    {
	    this.listefilmMB= listefilmMB;
	    }

		public String getLangue() {
			return langue;
		}

		public void setLangue(String langue) {
			this.langue = langue;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public int getDuree() {
			return duree;
		}

		public void setDuree(int duree) {
			this.duree = duree;
		}

		public Realisateur getRealisateur() {
			return realisateur;
		}

		public void setRealisateur(Realisateur realisateur) {
			this.realisateur = realisateur;
		}

		public List<Categorie> getCategories() {
			return categories;
		}

		public void setCategories(List<Categorie> categories) {
			this.categories = categories;
		}

		public List<Note> getNotes() {
			return notes;
		}

		public void setNotes(List<Note> notes) {
			this.notes = notes;
		}
	    
}
