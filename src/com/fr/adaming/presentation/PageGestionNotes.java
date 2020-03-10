package com.fr.adaming.presentation;

import java.util.List;

import com.fr.adaming.Main;
import com.fr.adaming.dao.FilmDao;
import com.fr.adaming.dao.NoteDao;
import com.fr.adaming.entity.Film;
import com.fr.adaming.entity.Note;

public class PageGestionNotes {

	private static NoteDao dao = new NoteDao();
	private static FilmDao daoFilm = new FilmDao();

	public static void intro() {

		int choix = -1;

		do {
			System.out.println("\n********************\t Page Gestion Notes \t ********************");
			System.out.println("1- pour voir la liste de tous les avis");
			System.out.println("2- pour voir Vos notes");
			System.out.println("3- pour voir les notes d'un film ou série");
			System.out.println("4- pour ajouter une note");
			System.out.println("5- pour modifier Vos notes");
			System.out.println("6- pour supprimer Vos notes");
			System.out.println("7- pour modifier une note"); 
			System.out.println("8- pour supprimer une note");
			System.out.println("0- pour revenir à la Page Principale");

			choix = Integer.parseInt(Main.scan.nextLine());

			switch (choix) {
			case 1:
				afficherList();
				break;
			case 2:
				afficherNotesUtilisateurConnecte();
				break;
			case 3:
				afficherNotesParFilm();
				break;
			case 4:
				ajouter();
				break;
			case 5:
				modifierNoteUtilisateurConnecte();
				break;
			case 6:
				supprimerNoteUtilisateurConnecte();
				break;
			case 7:
				modifier();
				break;
			case 8:
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

	public static List<Note> afficherList() {
		System.out.println("\n********************\t List Notes \t ********************");
		List<Note> notes = dao.readAll();
		System.out.println(notes);
		return notes;

	}

	public static List<Note> afficherNotesUtilisateurConnecte() {
		System.out.println("\n********************\t Afficher Vos Notes \t ********************");
		List<Note> notes = dao.readByUtilisateur(PageAuth.getConnectedUser());
		System.out.println(notes);
		return notes;

	}

	public static void afficherNotesParFilm() {
		System.out.println("\n********************\t Afficher Notes Par film\t ********************");
		System.out.println("Entrez l'ID du film : ");
		int id = Integer.valueOf(Main.scan.nextLine());

		List<Note> notes = dao.readByFilmOuSerie(daoFilm.readById(id));
		System.out.println(notes);

	}

	public static void ajouter() {
		System.out.println("\n********************\t Ajouter Note \t ********************");
		// L'utilisateur doit choisir le film/série
		List<Film> films = PageGestionMedia.chercherParNom();
		Film media = null;
		if (films.size() > 1) {
			// Dans le cas où l'appli a trouvé plusieurs media qui ont le même nom
			System.out.println("Vous devez faire un choix (num de ligne) : [0.." + (films.size() - 1) + "]");
			int id = Integer.valueOf(Main.scan.nextLine());
			media = films.get(id);
		} else if (!films.isEmpty()) {
			media = films.get(0);
		}

		System.err.println("Vous êtes entrain d'ajouter une note pour (" + media.getNom() + ")");
		System.out.println("Entrez une note entre 0 et 9 : ");
		int note = Integer.valueOf(Main.scan.nextLine());

		System.out.println("Donnez votre avis en quelque mots : ");
		String commentaire = Main.scan.nextLine();

		Note noteO = new Note(media, PageAuth.getConnectedUser(), note, commentaire);

		dao.create(noteO);

	}

	public static void modifierNoteUtilisateurConnecte() {
		System.out.println("\n********************\t Modifier votre Note \t ********************");
		// récupérer les notes que cet utilisateur a ajouté
		List<Note> list = afficherNotesUtilisateurConnecte();
		Note noteO = null;

		if (list.size() > 1) {
			// si on trouve plusieurs notes
			System.out.println("Vous devez faire un choix (num de ligne) : [0.." + (list.size() - 1) + "]");
			int id = Integer.valueOf(Main.scan.nextLine());
			noteO = list.get(id);
		} else if (!list.isEmpty()) {
			// si on trouve qu'une seule note
			noteO = list.get(0);
		}

		System.err.println("Vous êtes entrain de modifier la note pour (" + noteO.getId().getFilm().getNom() + ")");
		System.out.println("Entrez une note entre 0 et 9 : [" + noteO.getNote() + "]");
		try {
			int note = Integer.valueOf(Main.scan.nextLine());
			noteO.setNote(note);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

		System.out.println("Donnez votre avis en quelque mots : [" + noteO.getCommentaire() + "]");
		String commentaire = Main.scan.nextLine();
		if (!commentaire.equals("")) {
			noteO.setCommentaire(commentaire);
		}

		dao.update(noteO);

	}

	public static void supprimerNoteUtilisateurConnecte() {
		System.out.println("\n********************\t Supprimer votre Note \t ********************");
		// récupérer les notes que cet utilisateur a ajouté
		List<Note> list = afficherNotesUtilisateurConnecte();
		Note noteO = null;

		if (list.size() > 1) {
			// si on trouve plusieurs notes
			System.out.println("Vous devez faire un choix (num de ligne) : [0.." + (list.size() - 1) + "]");
			int id = Integer.valueOf(Main.scan.nextLine());
			noteO = list.get(id);
		} else if (!list.isEmpty()) {
			// si on trouve qu'une seule note
			noteO = list.get(0);
		}

		System.err
				.println("Vous êtes sur le point de supprimer la note pour (" + noteO.getId().getFilm().getNom() + ")");
		dao.delete(noteO);

	}

	public static void modifier() {
		System.out.println("\n********************\t Modifier Note \t ********************");
		// récupérer toutes les notes
		List<Note> list = afficherList();
		Note noteO = null;

		if (list.size() > 1) {
			// si on trouve plusieurs notes
			System.out.println("Vous devez faire un choix (num de ligne) : [0.." + (list.size() - 1) + "]");
			int id = Integer.valueOf(Main.scan.nextLine());
			noteO = list.get(id);
		} else if (!list.isEmpty()) {
			// si on trouve qu'une seule note
			noteO = list.get(0);
		}

		System.err.println("Vous êtes entrain de modifier la note pour (" + noteO.getId().getFilm().getNom() + ")");
		System.out.println("Entrez une note entre 0 et 9 : [" + noteO.getNote() + "]");
		try {
			int note = Integer.valueOf(Main.scan.nextLine());
			noteO.setNote(note);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

		System.out.println("Donnez votre avis en quelque mots : [" + noteO.getCommentaire() + "]");
		String commentaire = Main.scan.nextLine();
		if (!commentaire.equals("")) {
			noteO.setCommentaire(commentaire);
		}

		dao.update(noteO);

	}

	public static void supprimer() {
		System.out.println("\n********************\t Supprimer votre Note \t ********************");
		// récupérer toutes les notes
		List<Note> list = afficherList();
		Note noteO = null;

		if (list.size() > 1) {
			// Dans le cas où l'utilsiateur a ajouté plusieurs notes
			System.out.println("Vous devez faire un choix (num de ligne) : [0.." + (list.size() - 1) + "]");
			int id = Integer.valueOf(Main.scan.nextLine());
			noteO = list.get(id);
		} else if (!list.isEmpty()) {
			noteO = list.get(0);
		}

		System.err
				.println("Vous êtes sur le point de supprimer la note pour (" + noteO.getId().getFilm().getNom() + ")");
		dao.delete(noteO);

	}

}
