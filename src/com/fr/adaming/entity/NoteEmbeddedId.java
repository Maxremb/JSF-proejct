package com.fr.adaming.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class NoteEmbeddedId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_user")
	private Utilisateur user;

	@ManyToOne
	@JoinColumn(name = "id_film")
	private Film film;

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public NoteEmbeddedId() {
		super();
	}

	public NoteEmbeddedId(Utilisateur user, Film film) {
		super();
		this.user = user;
		this.film = film;
	}

}
