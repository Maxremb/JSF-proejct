package com.fr.adaming.presentation;

import com.fr.adaming.Main;

public class PageEspaceClient {
	
	public static void intro() {

		int choix = -1;

		do {

			System.out.println("\n********************\t Espace Client \t ********************");
			System.out.println("\t Bonjour " + PageAuth.getConnectedUser().getNom());
			System.out.println("1- pour d�couvrir nos Films et S�ries");
			System.out.println("2- pour donner un avis");
			System.out.println("3- pour voir vos notes");
			System.out.println("4- pour modifier vos notes");
			System.out.println("5- pour modifier vos informations perso.");
			System.out.println("6- d�sactiver votre compte");
			System.out.println("0- pour se d�connecter et revenir � la Page Principale");

			choix = Integer.parseInt(Main.scan.nextLine());

			switch (choix) {
			case 1:
				PageGestionMedia.afficherList();
				break;
			case 2:
				PageGestionNotes.ajouter();
				break;
			case 3:
				PageGestionNotes.afficherNotesUtilisateurConnecte();
				break;
			case 4:
				PageGestionNotes.modifierNoteUtilisateurConnecte();
				break;
			case 5:
				PageGestionUtilisateur.modifierInfoPerso();
				break;
			case 6:
				PageGestionUtilisateur.desactiverCeCompte();
				break;
			case 0:
				System.out.println("Traitement de la requ�te en cours...");
				PageAuth.disconnect();
				break;
			default:
				System.out.println("Erreur de saisie! R�essayer");
				break;
			}
		} while (choix != 0);
	}

}
