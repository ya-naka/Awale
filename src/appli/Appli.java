package appli;

public class Appli {

	public static void main(String[] args) {
		Mancala jeu;
		int varianteMancala;
		int joueurD�but;
		boolean varianteAwal�;

		try {
			varianteMancala = Integer.parseInt(args[0]);
		}catch(Exception e) {
			varianteMancala = 0;
		}
		
		try {
			joueurD�but = Integer.parseInt(args[1]) == 1 ? 1 : 0;
		}catch(Exception e) {
			joueurD�but = 0;
		}
		
		try {
			varianteAwal� = Integer.parseInt(args[2]) == 1 ? true : false;
		}catch(Exception e) {
			varianteAwal� = false;
		}
		
		jeu = FabriqueMancala.fabrique(varianteMancala, joueurD�but, varianteAwal�);
		System.out.println(jeu.variante() + System.lineSeparator());
		do {
			System.out.println(jeu.toString());
			System.out.println(jeu.afficheTour());
			jeu.choisirTrou();
			if(jeu.partieFinie())
				break;
			jeu.jouer();
			System.out.println(jeu.afficheGrainesRamass�es());
			jeu.joueurSuivant();
			jeu.peutContinuerLaPartie();
		}while(!jeu.partieFinie());
		System.out.println(jeu.gagnant());
	}
}
