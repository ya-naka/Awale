package mancala;

public class Awalé extends AwaléStandard{
	private boolean variante1; //variante dans laquelle on ne ramasse pas après le dernier trou semé

	public Awalé() {
		this(0, false);
	}
	
	/*
	 * @param joueur numéro du joueur qui commence la partie
	 * @param variante indication du mode de jeu
	 */
	public Awalé(int joueur, boolean variante) {
		super(joueur);
		this.variante1 = variante;
	}
	
	/*
	 * ramasse dans le dernier trou, les trous qui précèdent et qui suivent celui-ci, si c'est possible
	 */
	@Override
	public void ramasser() {
		super.ramasser();
		if(super.getGrainesRamassées() == 2 || super.getGrainesRamassées() == 3) {
			if(!this.variante1)
				this.ramasserAprès();
			this.ramasserAvant();
		}
	}
	
	/*
	 * ramasse les graines se trouvant après le dernier trou semé, si c'est possible
	 */
	public void ramasserAprès() {
		int trouSuivant = super.trouSuivant(super.getTrouFinSemis());
		while(this.peutRamasser(trouSuivant)) {
			super.ajouterGrainesRamassées(super.getTrou(trouSuivant).getGraines());
			super.getTrou(trouSuivant).vider();
			trouSuivant = super.trouSuivant(trouSuivant);
		}
	}
	
	/*
	 * ramasse les graines se trouvant avant le dernier trou semé, si c'est possible
	 */
	public void ramasserAvant() {
		int trouPrécédent = super.trouPrécédent(super.getTrouFinSemis());
		while(this.peutRamasser(trouPrécédent)) {
			super.ajouterGrainesRamassées(super.getTrou(trouPrécédent).getGraines());
			super.getTrou(trouPrécédent).vider();
			trouPrécédent = super.trouPrécédent(trouPrécédent);
		}
	}
	
	/*
	 * retourne vrai s'il reste au moins 2 trous non vides dans la rangée du joueur adverse
	 * @param trou index du trou concerné dans le tableau
	 * @return vrai si le joueur peut ramasser les graines présentes dans le trou
	 */
	@Override
	public boolean peutRamasser(int trou) {
		return super.peutRamasser(trou) && super.nbTrouRemplisDansRangéeDe((super.getJoueurActif()+1)%this.getNbJoueurs()) > 1;
	}
	
	/*
	 * ne peut pas semer dans le trou de départ
	 */
	@Override
	public boolean peutSemerDansTrouDeDépart() {
		return false;
	}
	
	/*
	 * si la partie est terminée, les graines restantes de la rangée de chaque joueur
	 * sont ajoutées au score de chacun
	 * @param joueur numéro du joueur concerné
	 */
	public int score(int joueur) {
		if(super.partieFinie())
			return super.score(joueur) + super.nbGrainesDansRangéeDe(joueur);
		else
			return super.score(joueur);
	}
}
