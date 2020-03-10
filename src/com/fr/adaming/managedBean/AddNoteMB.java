package com.fr.adaming.managedBean;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dao.CategorieDao;
import com.fr.adaming.dao.FilmDao;
import com.fr.adaming.dao.NoteDao;
import com.fr.adaming.dao.RealisateurDao;
import com.fr.adaming.dao.UtilisateurDao;
import com.fr.adaming.entity.Categorie;
import com.fr.adaming.entity.Film;
import com.fr.adaming.entity.Note;
import com.fr.adaming.entity.Realisateur;
import com.fr.adaming.entity.Utilisateur;

public class AddNoteMB {

	private int note;

	private String commentaire;

	private String film;

	private LoginMB loginMB = new LoginMB();

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String addNote() {
		System.out.println("DEBUG ADD NOTE !!" + loginMB.getUser());
		NoteDao nDao = new NoteDao();
		UtilisateurDao uDao = new UtilisateurDao();
		FilmDao fDao = new FilmDao();
		List<Film> liste = new ArrayList<Film>();
		Film film = fDao.readById(Integer.parseInt(this.film));
		Note notation = new Note(film, this.loginMB.getUser(), this.note, this.commentaire);
		nDao.create(notation);
		return "addNote.xhtml?faces-redirect=true";
	}
}
