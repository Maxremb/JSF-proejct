package com.fr.adaming.presentation;

import com.fr.adaming.Main;

public class PageEspaceAdmin {

	public static void intro() {

		int choix = -1;
		
		do {
			System.out.println("\n********************\t Espace Admin \t ********************");
			System.out.println("\t Bonjour " + PageAuth.getConnectedUser().getNom());
			System.out.println("1- pour gérer les films et les séries");
			System.out.println("2- pour gérer les notes");
			System.out.println("3- pour gérer les utilisateur");
			System.out.println("0- pour se déconnecter et revenir à la Page Principale");

			choix = Integer.parseInt(Main.scan.nextLine());

			switch (choix) {
			case 1:
				PageGestionMedia.intro();
				break;
			case 2:
				PageGestionNotes.intro();
				break;
			case 3:
				PageGestionUtilisateur.intro();
				break;
			case 0:
				System.out.println("Traitement de la requête en cours...");
				PageAuth.disconnect();
				break;
			default:
				System.out.println("Erreur de saisie! Réessayer");
				break;
			}
		} while (choix != 0);

	}

}
