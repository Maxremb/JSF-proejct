package com.fr.adaming.entity;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class Utilisateur implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30)
	private String nom;
	@Column(unique = true, length = 50, nullable = false)
	private String email;
	@Column(length= 20, nullable = false)
	private String pwd;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private RoleEnum role;
	@Column(nullable = false,length = 30)
	private boolean activated = false;
	@OneToMany(mappedBy = "id.user")
	private List<Note> notes;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
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

	public Utilisateur() {
		super();
	}

	public Utilisateur(String nom, String email, String pwd, RoleEnum role) {
		super();
		this.nom = nom;
		this.email = email;
		this.pwd = pwd;
		this.role = role;
	}

	public Utilisateur(String nom, String email, String pwd, RoleEnum role, boolean activated) {
		super();
		this.nom = nom;
		this.email = email;
		this.pwd = pwd;
		this.role = role;
		this.activated = activated;
	}

	@Override
	public String toString() {
		return "id=" + id + ", \tnom=" + nom + ", \temail=" + email + ", \tpwd=" + pwd + ", \trole=" + role
				+ ", \tactivated=" + activated + "\n";
	}

}
