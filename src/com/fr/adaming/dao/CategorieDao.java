package com.fr.adaming.dao;

import com.fr.adaming.dao.generic.AbstractDao;
import com.fr.adaming.entity.Categorie;

public class CategorieDao extends AbstractDao<Categorie, Integer>{
	
	public CategorieDao() {
		super(Categorie.class);
	}

}
