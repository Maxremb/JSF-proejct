package com.fr.adaming.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Realisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nomComplet;

	@OneToMany(mappedBy = "realisateur", cascade = CascadeType.REMOVE)
	private List<Film> films;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
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

	public Realisateur() {
		super();
	}

	public Realisateur(String nomComplet) {
		super();
		this.nomComplet = nomComplet;
	}

	@Override
	public String toString() {
		return "Realisateur [id=" + id + ", nomComplet=" + nomComplet + "]";
	}

}
