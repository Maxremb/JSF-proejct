package com.fr.adaming.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.fr.adaming.dao.FilmDao;
import com.fr.adaming.entity.Film;

public class ListFilmMB {
	
	private List<Film> listeFilms;
	
	private FilmDao filmDao = new FilmDao();
	
	private String nom;
	
	private String categorie;
	
	private String realisateur;
	
	private Film selectedFilm;
	
	private Double note;
	
	public Film getSelectedFilm() {
		return selectedFilm;
	}
	

	public Double getNote() {
		return note;
	}
	public void setNote(Film film) {
		this.note = filmDao.getRateAverageByFilm(film);
	}


	public String setSelectedFilm(Film selectedFilm) {
		this.selectedFilm = selectedFilm;
		System.out.println("DEBUG SELECTED FILM" + this.selectedFilm.getNom());
		return "singleFilm.xhtml?faces-redirect=true";
	}

	public FilmDao getFilmDao() {
		return filmDao;
	}

	public void setFilmDao(FilmDao filmDao) {
		this.filmDao = filmDao;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Film> getListeFilms() {
		return listeFilms;
	}

	public void setListeFilms(List<Film> listeFilms) {
		this.listeFilms = listeFilms;
	}

@PostConstruct
	public void listFilms() {
		this.listeFilms = filmDao.readAll();
	}
	
	public void listFilmsParNom() {
		System.out.println("DEBUG RECHERCHE PAR NOM");
		this.listeFilms = filmDao.searchByNom(this.nom);
	}
	
	public void listFilmsParCat() {
		this.listeFilms = filmDao.readByCategorie(this.categorie);
	}
	
	public void listFilmsParReal() {
		this.listeFilms = filmDao.searchByRealisateur(this.realisateur);
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getRealisateur() {
		return realisateur;
	}

	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	
	public String suppFilm(Film film) {
		try {
			filmDao.delete(film);
		} catch (Exception e) {
			System.out.println("erreur suppression");
		}
		this.listeFilms = filmDao.readAll();
		return "managedFilm.xhtml?faces-redirect=true";
	}
	
	public String onRowEdit(Film film) {
		FacesMessage msg = new FacesMessage("Edition", "Attribute has been changed");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		filmDao.update(film);
		this.listeFilms = filmDao.readAll();
		return "managedFilm.xhtml?faces-redirect=true";
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", "None has been changed");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	

	
	
	

		
}
