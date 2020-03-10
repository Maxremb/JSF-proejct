package com.fr.adaming.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.RowEditEvent;

import com.fr.adaming.dao.CategorieDao;
import com.fr.adaming.entity.Categorie;

public class ListCatMB {

	private List<Categorie> categories;

	private CategorieDao catDao = new CategorieDao();

	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(List<Categorie> categories) {
		this.categories = categories;
	}

	@PostConstruct
	public void init() {
		this.categories = catDao.readAll();
	}

	public String suppCat(Categorie cat) {
		try {
			catDao.delete(cat);
		} catch (Exception e) {
			System.out.println("erreur suppression");
		}
		this.categories = catDao.readAll();
		return "managedCat.xhtml?faces-redirect=true";
	}

//	public void updateCat(Categorie cat) {
//		cat.setNom(this.nom);
//		try {
//			catDao.update(cat);
//		} catch (Exception e) {
//			System.out.println("erreur update nom");
//			e.printStackTrace();
//		}
//		this.categories = catDao.readAll();
//	}

	public String addCat() {
		Categorie cat = new Categorie(this.nom);
		categories = catDao.readAll();
		boolean eq = true;
		for (Categorie categorie : categories) {
			if (categorie.getNom().equals(cat.getNom())) {
				eq = false;
			}
		}
		if (eq) {

			catDao.create(cat);

		} else {
			System.out.println("Catégorie déjà existante");
		}

		this.categories = catDao.readAll();
		this.nom = null;
		return "managedCat.xhtml?faces-redirect=true";
	}

	public String onRowEdit(Categorie cat) {
		FacesMessage msg = new FacesMessage("Name Edited", "name has been changed");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		catDao.update(cat);
		this.categories = catDao.readAll();
		return "managedCat.xhtml?faces-redirect=true";
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", "None has been changed");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}
