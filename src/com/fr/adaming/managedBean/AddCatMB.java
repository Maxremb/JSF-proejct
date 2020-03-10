package com.fr.adaming.managedBean;

import com.fr.adaming.dao.CategorieDao;
import com.fr.adaming.entity.Categorie;


public class AddCatMB {
	
	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void addCat() {
		CategorieDao catDao = new CategorieDao();
		
		Categorie cat = new Categorie(this.nom);
		
		catDao.create(cat);
	}
	
}
