package com.fr.adaming.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String nom;

	@ManyToMany(mappedBy = "categories", cascade = CascadeType.ALL)
	private List<Film> films;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Film> getFilms() {
		return films;
	}

	public void setFilms(List<Film> films) {
		this.films = films;
	}

	public void addFilm(Film film) {
		if (this.films == null) {
			this.films = new ArrayList<>();
		}
		this.films.add(film);
	}

	public Categorie() {
		super();
	}

	public Categorie(String nom) {
		super();
		this.nom = nom;
	}

	public Categorie(String nom, List<Film> films) {
		super();
		this.nom = nom;
		this.films = films;
	}

	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", films=" + films + "]";
	}

}
