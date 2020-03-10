package com.fr.adaming.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;

	@Column(length = 50)
	protected String langue;

	@Column(length = 100)
	protected String nom;

	@Column(length = 15)
	protected int duree;

	@ManyToOne
	@JoinColumn(name = "id_realisateur")
	protected Realisateur realisateur;

	@ManyToMany
	@JoinTable(name = "film_categ")
	protected List<Categorie> categories;

	@OneToMany(mappedBy = "id.film")
	protected List<Note> notes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public void addNote(Note note) {
		if (this.notes == null) {
			this.notes = new ArrayList<>();
		}
		this.notes.add(note);
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	public void addCategorie(Categorie categorie) {
		if (this.categories == null) {
			this.categories = new ArrayList<>();
		}
		this.categories.add(categorie);
	}

	public Film() {
		super();
	}

	public Film(String langue, String nom, int duree) {
		super();
		this.langue = langue;
		this.nom = nom;
		this.duree = duree;
	}

	public Film(String langue, String nom, int duree, Realisateur realisateur) {
		super();
		this.langue = langue;
		this.nom = nom;
		this.duree = duree;
		this.realisateur = realisateur;
	}

	public Film(String langue, String nom, int duree, Realisateur realisateur, List<Categorie> categories) {
		super();
		this.langue = langue;
		this.nom = nom;
		this.duree = duree;
		this.realisateur = realisateur;
		this.categories = categories;
	}

	@Override
	public String toString() {
		return "\tid=" + id + ", \tnom=" + nom + ", \tlangue=" + langue + ", \tduree=" + duree + "\n";
	}

}
