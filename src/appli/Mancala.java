package appli;

public interface Mancala {
	//conditions
	boolean peutRamasser(int trou);
	boolean peutViderTrou(int trou);
	void peutContinuerLaPartie();
	boolean partieFinie();
	
	//saisie
	void choisirTrou();
	
	//action
	void jouer();
	void semer();
	void ramasser();
	
	//outil
	void joueurSuivant();
	
	//affichage
	String toString();
	String afficheTour();
	String gagnant();
	String afficheGrainesRamassées();
	String variante();
}
