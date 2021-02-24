package appli;

public class Appli {

	public static void main(String[] args) {
		Mancala jeu;
		int varianteMancala;
		int joueurDébut;
		boolean varianteAwalé;

		try {
			varianteMancala = Integer.parseInt(args[0]);
		}catch(Exception e) {
			varianteMancala = 0;
		}
		
		try {
			joueurDébut = Integer.parseInt(args[1]) == 1 ? 1 : 0;
		}catch(Exception e) {
			joueurDébut = 0;
		}
		
		try {
			varianteAwalé = Integer.parseInt(args[2]) == 1 ? true : false;
		}catch(Exception e) {
			varianteAwalé = false;
		}
		
		jeu = FabriqueMancala.fabrique(varianteMancala, joueurDébut, varianteAwalé);
		System.out.println(jeu.variante() + System.lineSeparator());
		do {
			System.out.println(jeu.toString());
			System.out.println(jeu.afficheTour());
			jeu.choisirTrou();
			if(jeu.partieFinie())
				break;
			jeu.jouer();
			System.out.println(jeu.afficheGrainesRamassées());
			jeu.joueurSuivant();
			jeu.peutContinuerLaPartie();
		}while(!jeu.partieFinie());
		System.out.println(jeu.gagnant());
	}
}
