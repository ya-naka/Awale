package mancala;

public class Awal� extends Awal�Standard{
	private boolean variante1; //variante dans laquelle on ne ramasse pas apr�s le dernier trou sem�

	public Awal�() {
		this(0, false);
	}
	
	/*
	 * @param joueur num�ro du joueur qui commence la partie
	 * @param variante indication du mode de jeu
	 */
	public Awal�(int joueur, boolean variante) {
		super(joueur);
		this.variante1 = variante;
	}
	
	/*
	 * ramasse dans le dernier trou, les trous qui pr�c�dent et qui suivent celui-ci, si c'est possible
	 */
	@Override
	public void ramasser() {
		super.ramasser();
		if(super.getGrainesRamass�es() == 2 || super.getGrainesRamass�es() == 3) {
			if(!this.variante1)
				this.ramasserApr�s();
			this.ramasserAvant();
		}
	}
	
	/*
	 * ramasse les graines se trouvant apr�s le dernier trou sem�, si c'est possible
	 */
	public void ramasserApr�s() {
		int trouSuivant = super.trouSuivant(super.getTrouFinSemis());
		while(this.peutRamasser(trouSuivant)) {
			super.ajouterGrainesRamass�es(super.getTrou(trouSuivant).getGraines());
			super.getTrou(trouSuivant).vider();
			trouSuivant = super.trouSuivant(trouSuivant);
		}
	}
	
	/*
	 * ramasse les graines se trouvant avant le dernier trou sem�, si c'est possible
	 */
	public void ramasserAvant() {
		int trouPr�c�dent = super.trouPr�c�dent(super.getTrouFinSemis());
		while(this.peutRamasser(trouPr�c�dent)) {
			super.ajouterGrainesRamass�es(super.getTrou(trouPr�c�dent).getGraines());
			super.getTrou(trouPr�c�dent).vider();
			trouPr�c�dent = super.trouPr�c�dent(trouPr�c�dent);
		}
	}
	
	/*
	 * retourne vrai s'il reste au moins 2 trous non vides dans la rang�e du joueur adverse
	 * @param trou index du trou concern� dans le tableau
	 * @return vrai si le joueur peut ramasser les graines pr�sentes dans le trou
	 */
	@Override
	public boolean peutRamasser(int trou) {
		return super.peutRamasser(trou) && super.nbTrouRemplisDansRang�eDe((super.getJoueurActif()+1)%this.getNbJoueurs()) > 1;
	}
	
	/*
	 * ne peut pas semer dans le trou de d�part
	 */
	@Override
	public boolean peutSemerDansTrouDeD�part() {
		return false;
	}
	
	/*
	 * si la partie est termin�e, les graines restantes de la rang�e de chaque joueur
	 * sont ajout�es au score de chacun
	 * @param joueur num�ro du joueur concern�
	 */
	public int score(int joueur) {
		if(super.partieFinie())
			return super.score(joueur) + super.nbGrainesDansRang�eDe(joueur);
		else
			return super.score(joueur);
	}
}
