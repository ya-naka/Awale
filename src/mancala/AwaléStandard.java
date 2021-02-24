package mancala;

import java.util.Scanner;

import appli.Mancala;
import trou.Trou;

public abstract class AwaléStandard implements Mancala{
	private Trou[] trous;
	private int nbTrous;
	private static final int NB_JOUEURS = 2;
	private int joueurActif; // 0 pour SUD, 1 pour NORD
	private int scoreNord;
	private int scoreSud;
	private boolean finPartie;
	private int trouDépartSemis;
	private int trouFinSemis;
	private int grainesRamassées;
	private boolean tourComplet;
	
	public AwaléStandard() {
		this(0);
	}
	
	/*
	 * @param joueurActif numéro du joueur qui commence la partie
	 */
	public AwaléStandard(int joueurActif) {
		this(joueurActif, 12, 4);
	}
	
	/*
	 * @param joueurActif numéro du joueur qui commence la partie
	 * @param nbTrous nombre de trous composant le plateau
	 */
	public AwaléStandard(int joueurActif, int nbTrous, int nbGrainesAuDépart) {
		assert(nbTrous > 0 && nbGrainesAuDépart >= 0);
		this.nbTrous = nbTrous;
		this.trous = new Trou[nbTrous];
		for(int i = 0; i < this.trous.length; ++i)
			this.trous[i] = new Trou(nbGrainesAuDépart);
		this.joueurActif = joueurActif;
		this.scoreNord = 0;
		this.scoreSud = 0;
		this.finPartie = false;
		this.trouDépartSemis = 1;
		this.trouFinSemis = 0;
		this.grainesRamassées = 0;
		this.tourComplet = false;
	}
	
	
	/*
	 * @return le nombre de trous dont est composé de plateau  
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
	 * @return le nombre de graines ramassées pendant le tour
	 */
	public int getGrainesRamassées() {
		return this.grainesRamassées;
	}

	/*
	 * @return l'index du premier trou du semis
	 */
	public int getTrouDépartSemis() {
		return this.trouDépartSemis;
	}
	
	/*
	 * @return l'index du dernier trou du semis
	 */
	public int getTrouFinSemis() {
		return this.trouFinSemis;
	}
	
	/*
	 * @param trou position à laquelle se trouve le trou recherché
	 * @return le trou demandé
	 */
	public Trou getTrou(int trou) {
		return this.trous[trou];
	}
	
	/*
	 * @return le numéro du joueur qui est en train de jouer
	 */
	public int getJoueurActif() {
		return this.joueurActif;
	}

	/*
	 * @param joueur numéro du joueur concerné
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
	 * @return vrai si un tour complet du plateau a été effectué pendant le semis
	 */
	public boolean getTourComplet() {
		return this.tourComplet;
	}


	/*
	 * ajoute à la propriété grainesRamassées le nombre envoyé en paramètre
	 * @param graines nombre de graines à ajouter à la propriété
	 */
	public void ajouterGrainesRamassées(int graines) {
		assert(graines >= 0);
		this.grainesRamassées += graines;
	}
	
	/*
	 * passe au joueur suivant si la rangée de celui-ci n'est pas vide
	 * réinitialise les attributs propres au tour du joueur
	 */
	public void joueurSuivant() {
		this.actualiserScore();
		this.grainesRamassées = 0;
		this.tourComplet = false;
		if(this.nbTrouRemplisDansRangéeDe(((this.joueurActif)+1)%NB_JOUEURS) != 0)
			this.joueurActif = (this.joueurActif+1)%NB_JOUEURS;
	}
	
	/*
	 * ajoute les graines ramassées pendant le tour
	 * au score du joueur actif
	 */
	public void actualiserScore() {
		if(this.joueurActif == 0)
			this.scoreSud += this.grainesRamassées;
		else
			this.scoreNord += this.grainesRamassées;
	}
	
	/*
	 * redéfini la valeur de la propriété trouDépartSemis
	 * @param trouDépart nouveau trou de départ du semis
	 */
	public void choisirTrou(int trouDépart) {
		assert(trouDépart >= 0 && trouDépart < getNbTrous());
		this.trouDépartSemis = trouDépart;
	}
	
	/*
	 * saisie du joueur
	 * si elle est invalide : affiche un message d'erreur et redemande la saisie
	 * si la valeur saisie est 0, arrête la partie
	 * sinon, défini la valeur de trouDépartSemis par celle de saisie (-1)
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
			this.trouDépartSemis = trouChoisi-1;
	}
	
	/*
	 * vérifie si la saisie est valide
	 * @param saisie nombre saisi
	 * @return vrai si la saisie est valide
	 */
	public boolean saisieValide(int saisie) {
		return saisie >= 0 && saisie <= getNbTrous();
	}

	/*
	 * retourne vrai si un joueur peut vider un trou pour commencer un semis
	 * @param trou index du trou concerné dans le tableau
	 * @return vrai si le trou peut être vidé
	 */
	@Override
	public boolean peutViderTrou(int trou) {
		assert(trou >= 0 && trou < getNbTrous());
		return (this.trous[trou].getGraines() > 0) && (this.joueurActif == trou/(getNbTrous()/NB_JOUEURS));
	}

	/*
	 * @return vrai si on peut semer dans le trou de départ du semis
	 */
	public abstract boolean peutSemerDansTrouDeDépart();
	
	/*
	 * @param trou index du trou concerné dans le tableau
	 * @return vrai si on peut semer dans le trou
	 */
	public boolean peutSemer(int trou) {
		assert(trou >= 0 && trou < getNbTrous());
		return trou != this.trouDépartSemis || peutSemerDansTrouDeDépart();
	}

	/*
	 * retourne vrai si les graines se trouvent dans la rangée
	 * adverse et si le trou contient 2 ou 3 graines
	 * @param trou index du trou concerné dans le tableau
	 * @return vrai si les graines du trou peuvent être ramassées
	 */
	@Override
	public boolean peutRamasser(int trou) {
		assert(trou >= 0 && trou < getNbTrous());
		return ((((this.joueurActif)+1)%NB_JOUEURS) == (trou/(getNbTrous()/NB_JOUEURS))) 
				&& (this.trous[trou].getGraines() == 2 || this.trous[trou].getGraines() == 3);
	}

	/*
	 * actualise l'état de la partie, elle est arrêtée s'il reste 2 graines ou moins sur le plateau
	 */
	@Override
	public void peutContinuerLaPartie() {
		if(nbGrainesDansRangéeDe(0) + nbGrainesDansRangéeDe(1) <= 2)
			this.finPartie = true;
	}
	
	/*
	 * déroulé classique d'un tour
	 */
	@Override
	public void jouer() {
		this.semer();
		this.ramasser();
	}
	
	/*
	 * sème les graines une à une à partir du trou
	 * de départ qui est vidé.
	 * sème dans le trou de départ si c'est possible
	 */
	@Override
	public void semer() {
		assert(peutViderTrou(this.trouDépartSemis));
		int nbGraines = this.trous[this.trouDépartSemis].getGraines();
		this.trous[this.trouDépartSemis].vider();
		int trouSuivant = trouSuivant(this.trouDépartSemis);
		while(nbGraines > 0) {
			if(trouSuivant == this.trouDépartSemis)
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
	 * ramasse les graines après avoir semé, si c'est possible
	 */
	@Override
	public void ramasser() {
		if(this.peutRamasser(this.trouFinSemis)) {
			this.ajouterGrainesRamassées(this.trous[this.trouFinSemis].getGraines());
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
	 * obtenir le trou qui précède sur le plateau
	 * @param trou emplacement actuel
	 * @return trou précédent
	 */
	public int trouPrécédent(int trou) {
		return trou == 0 ? getNbTrous()-1 : --trou;
	}
	
	/*
	 * @param joueur numéro du joueur concerné
	 * @return nombre de trous remplis dans la rangée du joueur
	 */
	public int nbTrouRemplisDansRangéeDe(int joueur) {
		int nbTrousRemplis = 0;
		for(int i = 6 * joueur; i < (getNbTrous()/NB_JOUEURS)*(joueur+1); ++i) {
			if(!this.trous[i].estVide())
				++nbTrousRemplis;
		}
		return nbTrousRemplis;
	}

	/*
	 * @param joueur numéro du joueur concerné
	 * @return nombre de graines dans la rangée du joueur
	 */
	public int nbGrainesDansRangéeDe(int joueur) {
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
			s += "Egalité. Aucun joueur n'";
		else if(score(0) > score(1))
			s += "SUD ";
		else
			s += "NORD ";
		return s + "a gagné la partie de " + this.variante();
	}
	
	/*
	 * @return le nombre de graines ramassées pendant le tour
	 */
	public String afficheGrainesRamassées() {
		return System.lineSeparator() + (this.joueurActif == 0 ? "SUD" : "NORD") + " a ramassé " + getGrainesRamassées() + " graine(s)" + System.lineSeparator();
	}
	
	/*
	 * @return le nom de la variante
	 */
	public String variante() {
		return this.getClass().toString().substring(14);
	}
}
