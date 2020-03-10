package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Note {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;

	@EmbeddedId
	private NoteEmbeddedId id; // PK composée

	@Column(length = 1)
	private int note;

	@Column(length = 500)
	private String commentaire;

//	@ManyToOne
//	@JoinColumn(name = "id_user")
//	private Utilisateur user;
//
//	@ManyToOne
//	@JoinColumn(name = "id_film")
//	private Film film;

	public int getNote() {
		return note;
	}

	public NoteEmbeddedId getId() {
		return id;
	}

	public void setId(NoteEmbeddedId id) {
		this.id = id;
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

	public Note() {
		super();
	}

	public Note(int note, String commentaire) {
		super();
		this.note = note;
		this.commentaire = commentaire;
	}

	public Note(NoteEmbeddedId id, int note, String commentaire) {
		super();
		this.id = id;
		this.note = note;
		this.commentaire = commentaire;
	}

	public Note(Film film, Utilisateur user, int note, String commentaire) {
		super();
		this.id = new NoteEmbeddedId(user, film);
		this.note = note;
		this.commentaire = commentaire;
	}

	@Override
	public String toString() {
		return "note=" + note + ", \tcommentaire=" + commentaire + ", \t film= " + this.id.getFilm().nom + ", \tuser="
				+ this.id.getUser().getNom() + "\n";
	}

}
