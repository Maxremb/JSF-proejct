package com.fr.adaming.managedBean;

import com.fr.adaming.dao.RealisateurDao;
import com.fr.adaming.entity.Realisateur;

public class AddRealMB {
	
	private String nomComplet;

	public String getNomComplet() {
		return nomComplet;
	}

	public void setNomComplet(String nomComplet) {
		this.nomComplet = nomComplet;
	}

	public void addReal() {
		RealisateurDao realDao = new RealisateurDao();
		
		Realisateur real = new Realisateur(this.nomComplet);
		
		realDao.create(real);
	}
	
}
