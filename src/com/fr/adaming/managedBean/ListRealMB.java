package com.fr.adaming.managedBean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import com.fr.adaming.dao.RealisateurDao;
import com.fr.adaming.entity.Categorie;
import com.fr.adaming.entity.Realisateur;

public class ListRealMB {
	
	private List<Realisateur> realisateurs;
	
	private RealisateurDao realDao = new RealisateurDao();
	
	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Realisateur> getRealisateurs() {
		return realisateurs;
	}

	public void setRealisateurs(List<Realisateur> realisateurs) {
		this.realisateurs = realisateurs;
	}

	@PostConstruct
	public void init() {
		this.realisateurs = realDao.readAll();
	}
	
	public String suppReal (Realisateur real) {
		try {
			realDao.delete(real);
		} catch (Exception e) {
			System.out.println("erreur suppression");
		}
		this.realisateurs = realDao.readAll();
		return "managedReal.xhtml?faces-redirect=true";
	}
	
	public void updateReal(Realisateur real) {
		real.setNomComplet(this.nom);
		try {
			realDao.update(real);
		} catch (Exception e) {
			System.out.println("erreur update nom");
			e.printStackTrace();
		}
		this.realisateurs = realDao.readAll();
	}
	
	public String addReal() {		
		Realisateur real = new Realisateur(this.nom);
		realDao.create(real);
		this.realisateurs = realDao.readAll();
		this.nom = null;
		return "managedReal.xhtml?faces-redirect=true";
	}
	
	public void onRowEdit(Realisateur real) {
        FacesMessage msg = new FacesMessage("Name Edited", "name has been changed");
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        realDao.update(real);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "None has been changed");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
