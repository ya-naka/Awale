package mancala;

import java.util.Scanner;

import appli.Mancala;
import trou.Trou;

public abstract class Awal�Standard implements Mancala{
	private Trou[] trous;
	private int nbTrous;
	private static final int NB_JOUEURS = 2;
	private int joueurActif; // 0 pour SUD, 1 pour NORD
	private int scoreNord;
	private int scoreSud;
	private boolean finPartie;
	private int trouD�partSemis;
	private int trouFinSemis;
	private int grainesRamass�es;
	private boolean tourComplet;
	
	public Awal�Standard() {
		this(0);
	}
	
	/*
	 * @param joueurActif num�ro du joueur qui commence la partie
	 */
	public Awal�Standard(int joueurActif) {
		this(joueurActif, 12, 4);
	}
	
	/*
	 * @param joueurActif num�ro du joueur qui commence la partie
	 * @param nbTrous nombre de trous composant le plateau
	 */
	public Awal�Standard(int joueurActif, int nbTrous, int nbGrainesAuD�part) {
		assert(nbTrous > 0 && nbGrainesAuD�part >= 0);
		this.nbTrous = nbTrous;
		this.trous = new Trou[nbTrous];
		for(int i = 0; i < this.trous.length; ++i)
			this.trous[i] = new Trou(nbGrainesAuD�part);
		this.joueurActif = joueurActif;
		this.scoreNord = 0;
		this.scoreSud = 0;
		this.finPartie = false;
		this.trouD�partSemis = 1;
		this.trouFinSemis = 0;
		this.grainesRamass�es = 0;
		this.tourComplet = false;
	}
	
	
	/*
	 * @return le nombre de trous dont est compos� de plateau  
	 */
	public int getNbTrous() {
		return this.nbTrous;
	}
	
	/*
	 * @return le nombre de joueurs
	 */
	public int getNbJoueurs(){
		return NB_JOUEURS;
	}
	
	/*
	 * @return le nombre de graines ramass�es pendant le tour
	 */
	public int getGrainesRamass�es() {
		return this.grainesRamass�es;
	}

	/*
	 * @return l'index du premier trou du semis
	 */
	public int getTrouD�partSemis() {
		return this.trouD�partSemis;
	}
	
	/*
	 * @return l'index du dernier trou du semis
	 */
	public int getTrouFinSemis() {
		return this.trouFinSemis;
	}
	
	/*
	 * @param trou position � laquelle se trouve le trou recherch�
	 * @return le trou demand�
	 */
	public Trou getTrou(int trou) {
		return this.trous[trou];
	}
	
	/*
	 * @return le num�ro du joueur qui est en train de jouer
	 */
	public int getJoueurActif() {
		return this.joueurActif;
	}

	/*
	 * @param joueur num�ro du joueur concern�
	 * @return le score du joueur
	 */
	public int score(int joueur) {
		return joueur == 0 ? this.scoreSud : this.scoreNord;
	}

	/*
	 * @return vrai si la partie est finie
	 */
	public boolean partieFinie() {
		return this.finPartie;
	}
	
	/*
	 * @return vrai si un tour complet du plateau a �t� effectu� pendant le semis
	 */
	public boolean getTourComplet() {
		return this.tourComplet;
	}


	/*
	 * ajoute � la propri�t� grainesRamass�es le nombre envoy� en param�tre
	 * @param graines nombre de graines � ajouter � la propri�t�
	 */
	public void ajouterGrainesRamass�es(int graines) {
		assert(graines >= 0);
		this.grainesRamass�es += graines;
	}
	
	/*
	 * passe au joueur suivant si la rang�e de celui-ci n'est pas vide
	 * r�initialise les attributs propres au tour du joueur
	 */
	public void joueurSuivant() {
		this.actualiserScore();
		this.grainesRamass�es = 0;
		this.tourComplet = false;
		if(this.nbTrouRemplisDansRang�eDe(((this.joueurActif)+1)%NB_JOUEURS) != 0)
			this.joueurActif = (this.joueurActif+1)%NB_JOUEURS;
	}
	
	/*
	 * ajoute les graines ramass�es pendant le tour
	 * au score du joueur actif
	 */
	public void actualiserScore() {
		if(this.joueurActif == 0)
			this.scoreSud += this.grainesRamass�es;
		else
			this.scoreNord += this.grainesRamass�es;
	}
	
	/*
	 * red�fini la valeur de la propri�t� trouD�partSemis
	 * @param trouD�part nouveau trou de d�part du semis
	 */
	public void choisirTrou(int trouD�part) {
		assert(trouD�part >= 0 && trouD�part < getNbTrous());
		this.trouD�partSemis = trouD�part;
	}
	
	/*
	 * saisie du joueur
	 * si elle est invalide : affiche un message d'erreur et redemande la saisie
	 * si la valeur saisie est 0, arr�te la partie
	 * sinon, d�fini la valeur de trouD�partSemis par celle de saisie (-1)
	 */
	public void choisirTrou() {
		Scanner scan = new Scanner(System.in);
		int trouChoisi;
		do {
			do {
				trouChoisi = scan.nextInt();
				if(!saisieValide(trouChoisi))
					System.out.println("Saisie invalide. Veuillez recommencer");
			}while(!saisieValide(trouChoisi));
			if(trouChoisi == 0)
				break;
			if(!this.peutViderTrou(trouChoisi-1))
				System.out.println("Saisie invalide. Veuillez recommencer");
		}while(!this.peutViderTrou(trouChoisi-1));
		if(trouChoisi == 0) {
			this.finPartie = true;
		}else 
			this.trouD�partSemis = trouChoisi-1;
	}
	
	/*
	 * v�rifie si la saisie est valide
	 * @param saisie nombre saisi
	 * @return vrai si la saisie est valide
	 */
	public boolean saisieValide(int saisie) {
		return saisie >= 0 && saisie <= getNbTrous();
	}

	/*
	 * retourne vrai si un joueur peut vider un trou pour commencer un semis
	 * @param trou index du trou concern� dans le tableau
	 * @return vrai si le trou peut �tre vid�
	 */
	@Override
	public boolean peutViderTrou(int trou) {
		assert(trou >= 0 && trou < getNbTrous());
		return (this.trous[trou].getGraines() > 0) && (this.joueurActif == trou/(getNbTrous()/NB_JOUEURS));
	}

	/*
	 * @return vrai si on peut semer dans le trou de d�part du semis
	 */
	public abstract boolean peutSemerDansTrouDeD�part();
	
	/*
	 * @param trou index du trou concern� dans le tableau
	 * @return vrai si on peut semer dans le trou
	 */
	public boolean peutSemer(int trou) {
		assert(trou >= 0 && trou < getNbTrous());
		return trou != this.trouD�partSemis || peutSemerDansTrouDeD�part();
	}

	/*
	 * retourne vrai si les graines se trouvent dans la rang�e
	 * adverse et si le trou contient 2 ou 3 graines
	 * @param trou index du trou concern� dans le tableau
	 * @return vrai si les graines du trou peuvent �tre ramass�es
	 */
	@Override
	public boolean peutRamasser(int trou) {
		assert(trou >= 0 && trou < getNbTrous());
		return ((((this.joueurActif)+1)%NB_JOUEURS) == (trou/(getNbTrous()/NB_JOUEURS))) 
				&& (this.trous[trou].getGraines() == 2 || this.trous[trou].getGraines() == 3);
	}

	/*
	 * actualise l'�tat de la partie, elle est arr�t�e s'il reste 2 graines ou moins sur le plateau
	 */
	@Override
	public void peutContinuerLaPartie() {
		if(nbGrainesDansRang�eDe(0) + nbGrainesDansRang�eDe(1) <= 2)
			this.finPartie = true;
	}
	
	/*
	 * d�roul� classique d'un tour
	 */
	@Override
	public void jouer() {
		this.semer();
		this.ramasser();
	}
	
	/*
	 * s�me les graines une � une � partir du trou
	 * de d�part qui est vid�.
	 * s�me dans le trou de d�part si c'est possible
	 */
	@Override
	public void semer() {
		assert(peutViderTrou(this.trouD�partSemis));
		int nbGraines = this.trous[this.trouD�partSemis].getGraines();
		this.trous[this.trouD�partSemis].vider();
		int trouSuivant = trouSuivant(this.trouD�partSemis);
		while(nbGraines > 0) {
			if(trouSuivant == this.trouD�partSemis)
				this.tourComplet = true;
			if(this.peutSemer(trouSuivant)) {
				this.trous[trouSuivant].ajouterGraine();
				--nbGraines;
			}
			if(nbGraines == 0)
				this.trouFinSemis = trouSuivant;
			else
				trouSuivant = trouSuivant(trouSuivant);
		}
	}

	/*
	 * ramasse les graines apr�s avoir sem�, si c'est possible
	 */
	@Override
	public void ramasser() {
		if(this.peutRamasser(this.trouFinSemis)) {
			this.ajouterGrainesRamass�es(this.trous[this.trouFinSemis].getGraines());
			this.trous[this.trouFinSemis].vider();
		}
	}

	/*
	 * obtenir le trou qui suit sur le plateau
	 * @param trou emplacement actuel
	 * @return trou suivant
	 */
	public int trouSuivant(int trou) {
		return (++trou)%getNbTrous();
	}
	
	/*
	 * obtenir le trou qui pr�c�de sur le plateau
	 * @param trou emplacement actuel
	 * @return trou pr�c�dent
	 */
	public int trouPr�c�dent(int trou) {
		return trou == 0 ? getNbTrous()-1 : --trou;
	}
	
	/*
	 * @param joueur num�ro du joueur concern�
	 * @return nombre de trous remplis dans la rang�e du joueur
	 */
	public int nbTrouRemplisDansRang�eDe(int joueur) {
		int nbTrousRemplis = 0;
		for(int i = 6 * joueur; i < (getNbTrous()/NB_JOUEURS)*(joueur+1); ++i) {
			if(!this.trous[i].estVide())
				++nbTrousRemplis;
		}
		return nbTrousRemplis;
	}

	/*
	 * @param joueur num�ro du joueur concern�
	 * @return nombre de graines dans la rang�e du joueur
	 */
	public int nbGrainesDansRang�eDe(int joueur) {
		int nbGraines = 0;
		for(int i = 6 * joueur; i < (getNbTrous()/NB_JOUEURS)*(joueur+1); ++i) {
			nbGraines += this.trous[i].getGraines();
		}
		return nbGraines;
	}
	
	/*
	 * @return l'affichage du plateau de jeu avec le score des joueurs
	 */
	public String toString() {
		String s = "NORD  ";
		for(int i = getNbTrous(); i > (getNbTrous()/NB_JOUEURS); --i) {
			if(i < 10)
				s += " ";
			s += i + "   ";
		}
		
		s += System.lineSeparator() + "    ";
		for(int i = getNbTrous()-1; i >= (getNbTrous()/NB_JOUEURS); --i) {
			s += " [";
			if(this.trous[i].getGraines() < 10)
				s += " ";
			s += this.trous[i].toString() + "]";
		}
		s += System.lineSeparator() + "    ";
		for(int i = 0; i < (getNbTrous()/NB_JOUEURS); ++i) {
			s += " [";
			if(this.trous[i].getGraines() < 10)
				s += " ";
			s += this.trous[i].toString() + "]";
		}
		
		s += System.lineSeparator() + "SUD";
		for(int i = 1; i <= (getNbTrous()/NB_JOUEURS); ++i)
			s += "    " + i;
		
		s += afficheScore();
		
		return s;
	}
	
	/*
	 * @return l'instruction du tour
	 */
	public String afficheTour(){
		return System.lineSeparator() + "A " + (this.joueurActif == 0 ? "SUD" : "NORD") + " de jouer";
	}

	/*
	 * @return le score des joueurs
	 */
	public String afficheScore() {
		String s = System.lineSeparator();
		s += System.lineSeparator() + "NORD a " + score(1) + " graine(s)";
		 return s + System.lineSeparator() + "SUD a " + score(0) + " graine(s)";
	}
	
	/*
	 * @return le gagnant de la partie
	 */
	public String gagnant() {
		assert(partieFinie());
		String s = afficheScore() + System.lineSeparator();
		if(score(0) == score(1))
			s += "Egalit�. Aucun joueur n'";
		else if(score(0) > score(1))
			s += "SUD ";
		else
			s += "NORD ";
		return s + "a gagn� la partie de " + this.variante();
	}
	
	/*
	 * @return le nombre de graines ramass�es pendant le tour
	 */
	public String afficheGrainesRamass�es() {
		return System.lineSeparator() + (this.joueurActif == 0 ? "SUD" : "NORD") + " a ramass� " + getGrainesRamass�es() + " graine(s)" + System.lineSeparator();
	}
	
	/*
	 * @return le nom de la variante
	 */
	public String variante() {
		return this.getClass().toString().substring(14);
	}
}
