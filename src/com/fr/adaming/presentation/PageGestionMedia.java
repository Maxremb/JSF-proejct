package com.fr.adaming.presentation;

import java.util.List;

import com.fr.adaming.Main;
import com.fr.adaming.dao.FilmDao;
import com.fr.adaming.entity.Film;
import com.fr.adaming.entity.Realisateur;

public class PageGestionMedia {

	private static FilmDao dao = new FilmDao();

	public static void intro() {

		int choix = -1;

		do {
			System.out.println("\n********************\t Page Gestion Film \t ********************");
			System.out.println("1- pour voir la liste des films");
			System.out.println("2- pour chercher un film par ID");
			System.out.println("3- pour chercher un film par Nom");
			System.out.println("4- pour chercher un film par Catégorie");
			System.out.println("5- pour ajouter un nouveau film");
			System.out.println("6- pour modifier un film");
			System.out.println("7- pour supprimer un film");
			System.out.println("0- pour revenir à la Page Principale");

			choix = Integer.parseInt(Main.scan.nextLine());

			switch (choix) {
			case 1:
				afficherList();
				break;
			case 2:
				chercherParId();
				break;
			case 3:
				chercherParNom();
				break;
			case 4:
				chercherParNomCategorie();
				break;
			case 5:
				ajouter();
				break;
			case 6:
				modifier();
				break;
			case 7:
				supprimer();
				break;
			case 0:
				System.out.println("Traitement de la requête en cours...");
				break;
			default:
				System.out.println("Erreur de saisie! Réessayer");
				break;
			}
		} while (choix != 0);
	}

	public static void afficherList() {
		System.out.println("\n********************\t List Film \t ********************");
		List<Film> films = dao.readAll();
		System.out.println(films);

	}

	public static Film chercherParId() {
		System.out.println("\n********************\t Chercher Film Par ID \t ********************");
		System.out.println("Entrez l'ID du film que vous chercher :");
		int id = Integer.parseInt(Main.scan.nextLine());
		Film media = dao.readById(id);
		System.out.println(media);
		System.out.println("la moyenne de note de ce film est " + dao.getRateAverageByFilm(media)+"/9");
		return media;

	}

	public static List<Film> chercherParNom() {
		System.out.println("\n********************\t Chercher Film Par NOM \t ********************");
		System.out.println("Entrez le nom du film/série que vous chercher :");
		String nom = Main.scan.nextLine();
		List<Film> medias = dao.readByNom(nom);
		System.out.println("On a trouvé " + medias.size() + " film(s) avec ce nom : ");
		System.out.println(medias);
		return medias;

	}

	public static void chercherParNomCategorie() {
		System.out.println("\n********************\t Chercher Film Par Catégorie \t ********************");
		System.out.println("Entrez la catégorie que vous chercher :");
		String nom = Main.scan.nextLine();
		List<Film> films = dao.readByCategorie(nom);
		System.out.println("On a trouvé " + films.size() + " film(s) avec ce nom : ");
		System.out.println(films);
	}

	public static void ajouter() {
		System.out.println("\n********************\t Ajouter Film \t********************");
		System.out.println("Tapez le nom du film : ");
		String nom = Main.scan.nextLine();

		System.out.println("Tapez la langue du film : ");
		String langue = Main.scan.nextLine();

		System.out.println("Tapez la durée du film : ");
		int duree = Integer.parseInt(Main.scan.nextLine());

		System.out.println("Tapez le nom du réalisateur : ");
		String nomR = Main.scan.nextLine();
		Realisateur realisateur = new Realisateur(nomR);

		dao.create(new Film(langue, nom, duree, realisateur));
	}

	public static void modifier() {
		System.out.println("\n********************\t Modifier Film \t********************");
		Film film = chercherParId();
		System.out.println("Tapez le nouveau nom du film : [" + film.getNom() + "]");
		String nom = Main.scan.nextLine();
		if (!nom.equals("")) {
			film.setNom(nom);
		}

		System.out.println("Tapez la langue du film : [" + film.getLangue() + "]");
		String langue = Main.scan.nextLine();
		if (!langue.equals("")) {
			film.setLangue(langue);
		}

		System.out.println("Tapez la durée du film : [" + film.getDuree() + "]");
		try {
			int duree = Integer.parseInt(Main.scan.nextLine());
			film.setDuree(duree);
		} catch (NumberFormatException e) {
//			film.setDuree(duree);
		}

		System.out.println("Tapez le nom du réalisateur : [" + film.getRealisateur().getNomComplet() + "]");
		String nomR = Main.scan.nextLine();
		if (!nomR.equals("")) {
			Realisateur realisateur = new Realisateur(nomR);
			film.setRealisateur(realisateur);
		}

		dao.update(film);

	}

	public static void supprimer() {
		System.out.println("\n********************\t Supprimer Film \t********************");
		System.out.println("Entrez l'ID du film que vous voulez supprimer :");
		int id = Integer.parseInt(Main.scan.nextLine());
		Film film = dao.readById(id);
		dao.delete(film);

	}

}
