package com.fr.adaming.managedBean;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dao.CategorieDao;
import com.fr.adaming.dao.FilmDao;
import com.fr.adaming.dao.RealisateurDao;
import com.fr.adaming.entity.Categorie;
import com.fr.adaming.entity.Film;
import com.fr.adaming.entity.Realisateur;

public class AddFilmMB {
	
	private String langue;

	private String nom;

	private int duree;

	private String realisateur;

	private List<String> categories;

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
	
	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}

	public String addFilm() {
		FilmDao fDao = new FilmDao();
		CategorieDao catDao = new CategorieDao();
		RealisateurDao realDao = new RealisateurDao();
		List<Categorie> liste = new ArrayList<Categorie>();
		for(String i : this.categories) {
			try {
			Categorie cat = catDao.readById(Integer.parseInt(i));
			liste.add(cat);
			}catch(NullPointerException npe) {
				npe.printStackTrace();
				System.out.println("DEBUG NPE");
			}
		}
		Realisateur real = realDao.readById(Integer.parseInt(this.realisateur));
		Film film = new Film(this.langue, this.nom, this.duree, real, liste);
		fDao.create(film);
		return "addFilm.xhtml?faces-redirect=true";
	}
}
