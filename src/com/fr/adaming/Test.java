package com.fr.adaming;

import java.util.List;

import com.fr.adaming.config.ConnectDb;
import com.fr.adaming.dao.CategorieDao;
import com.fr.adaming.dao.FilmDao;
import com.fr.adaming.dao.NoteDao;
import com.fr.adaming.dao.RealisateurDao;
import com.fr.adaming.dao.UtilisateurDao;
import com.fr.adaming.entity.Categorie;
import com.fr.adaming.entity.Film;
import com.fr.adaming.entity.Note;
import com.fr.adaming.entity.Realisateur;
import com.fr.adaming.entity.RoleEnum;
import com.fr.adaming.entity.Utilisateur;

public class Test {

	// Prepare DAOs
	private static UtilisateurDao utilisateurDao = new UtilisateurDao();
	private static RealisateurDao realisateurDao = new RealisateurDao();
	private static FilmDao filmDao = new FilmDao();
	private static NoteDao noteDao = new NoteDao();
	private static CategorieDao categorieDao = new CategorieDao();
	

	public static void main(String[] args) {
		ConnectDb.getSession();
		
		testCreatingData();
//		testFetchingStarWarsData();
//		testFetchingFriendsData();
//		testFetchingAllData();
		
//		testSearchingFilmByCategories();
	//	testManagedUser();
	}
	
	public static void testSearchingFilmByCategories() {
		
		List<Film> films = filmDao.readByCategorie("Science-fiction");
		System.out.println(films);
	}

	public static void testFetchingStarWarsData() {

		// Récupérer le film par son id
		Film starWaFilm = filmDao.readById(1);

		// Afficher les détails de ce film
		System.out.println("\t film nom : " + starWaFilm.getNom());
		System.out.println("\t film langue : " + starWaFilm.getLangue());
		System.out.println("\t film duree : " + starWaFilm.getDuree());
		System.out.println("\t film \t categorie id : " + starWaFilm.getCategories().get(0).getId());
		System.out.println("\t film \t categorie nom : " + starWaFilm.getCategories().get(0).getNom());
		System.out.println("\t film \t realisateur id : " + starWaFilm.getRealisateur().getId());
		System.out.println("\t film \t realisateur nom : " + starWaFilm.getRealisateur().getNomComplet());
		System.out.println("\t film \t note 1 \t user id : " + starWaFilm.getNotes().get(0).getId().getUser().getId());
		System.out.println("\t film \t note 1 \t user nom : " + starWaFilm.getNotes().get(0).getId().getUser().getNom());
		System.out.println("\t film \t note 1 \t user role : " + starWaFilm.getNotes().get(0).getId().getUser().getRole());
		System.out.println("\t film \t note 1 \t user pwd : " + starWaFilm.getNotes().get(0).getId().getUser().getPwd());
		System.out.println("\t film \t note 1 \t note : " + starWaFilm.getNotes().get(0).getNote());
		System.out.println("\t film \t note 1 \t commentaire : " + starWaFilm.getNotes().get(0).getCommentaire());

		System.out.println("\n\t film \t note 2 \t user id : " + starWaFilm.getNotes().get(1).getId().getUser().getId());
		System.out.println("\t film \t note 2 \t user nom : " + starWaFilm.getNotes().get(1).getId().getUser().getNom());
		System.out.println("\t film \t note 2 \t user role : " + starWaFilm.getNotes().get(1).getId().getUser().getRole());
		System.out.println("\t film \t note 2 \t user pwd : " + starWaFilm.getNotes().get(1).getId().getUser().getPwd());
		System.out.println("\t film \t note 1 \t note : " + starWaFilm.getNotes().get(1).getNote());
		System.out.println("\t film \t note 1 \t commentaire : " + starWaFilm.getNotes().get(1).getCommentaire());

		System.out.println("\n\t film \t note 3 \t user id : " + starWaFilm.getNotes().get(2).getId().getUser().getId());
		System.out.println("\t film \t note 3 \t user nom : " + starWaFilm.getNotes().get(2).getId().getUser().getNom());
		System.out.println("\t film \t note 3 \t user role : " + starWaFilm.getNotes().get(2).getId().getUser().getRole());
		System.out.println("\t film \t note 3 \t user pwd : " + starWaFilm.getNotes().get(2).getId().getUser().getPwd());
		System.out.println("\t film \t note 1 \t note : " + starWaFilm.getNotes().get(2).getNote());
		System.out.println("\t film \t note 1 \t commentaire : " + starWaFilm.getNotes().get(2).getCommentaire());
	}


	public static void testCreatingData() {
		// Prepare entities
		Utilisateur admin = new Utilisateur("admin", "admin@adaming.fr", "azerty", RoleEnum.ADMIN, true);
		Utilisateur user1 = new Utilisateur("nomUser1", "nom1@gmail.com", "1234", RoleEnum.USER, true);
		Utilisateur user2 = new Utilisateur("nomUser2", "nom2@gmail.com", "0000", RoleEnum.USER, true);
		Utilisateur user3 = new Utilisateur("nomUser3", "nom3@gmail.com", "4321", RoleEnum.USER, true);
		
		Realisateur realisateurStarWars = new Realisateur("realisateur1Name");
		Realisateur realisateurFriends = new Realisateur("realisateur2Name");
		
		Categorie categorie1 = new Categorie("Science-fiction");
		Categorie categorie2 = new Categorie("Comédie");
		
		Film starWars = new Film("eng", "Star Wars", 125, realisateurStarWars);
		starWars.addCategorie(categorie1);
		

		Note noteUser1StarWars = new Note(starWars, user1, 10, "excellent film! :)");
		Note noteUser2StarWars = new Note(starWars, user2, 9, "Top");
		Note noteUser3StarWars = new Note(starWars, user3, 9, "J'ai adoré <3");

		// test saving entities
		utilisateurDao.create(admin);

		utilisateurDao.create(user1);
		
		utilisateurDao.create(user2);
		
		utilisateurDao.create(user3);

		realisateurDao.create(realisateurStarWars);
		
		realisateurDao.create(realisateurFriends);

		categorieDao.create(categorie1);
		
		categorieDao.create(categorie2);

		filmDao.create(starWars);
		


		noteDao.create(noteUser1StarWars);
		noteDao.create(noteUser2StarWars);
		noteDao.create(noteUser3StarWars);

	}
	
	public static void testFetchingAllData() {
		
		System.out.println("\n*********** All Admins ***********");
		System.out.println(utilisateurDao.readAllAdmins());
		
		System.out.println("\n*********** All Clients ***********");
		System.out.println(utilisateurDao.readAllUsers());
		
		System.out.println("\n*********** All Realisateurs ***********");
		System.out.println(realisateurDao.readAll());
		
		System.out.println("\n*********** All Categories ***********");
		System.out.println(categorieDao.readAll());
		
		System.out.println("\n*********** All Films ***********");
		System.out.println(filmDao.readAll());
		
		System.out.println("\n*********** All Notes ***********");
		System.out.println(noteDao.readAll());
	}

		public static void testManagedUser() {
			Utilisateur admin = new Utilisateur("admin", "admin@adaming.fr", "azerty", RoleEnum.ADMIN, true);
			Utilisateur user = new Utilisateur("user", "u@u.fr", "azerty", RoleEnum.USER, true);
			Utilisateur user1 = new Utilisateur("user1", "1@1.fr", "azerty", RoleEnum.USER, true);
			Utilisateur user2 = new Utilisateur("user2", "2@2.fr", "azerty", RoleEnum.USER, true);
			
			UtilisateurDao userDao = new UtilisateurDao();
			
			userDao.create(admin);
			userDao.create(user);
			userDao.create(user1);
			userDao.create(user2);
		}
}
