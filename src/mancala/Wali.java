package mancala;

public class Wali extends AwaléStandard{

	public Wali() {
		this(0);
	}
	
	/*
	 * @param joueur numéro du joueur qui commence la partie
	 */
	public Wali(int joueur) {
		super(joueur);
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
	
	/*
	 * retourne vrai s'il y a au moins 1 graine dans le trou
	 * @param trou index du trou concerné dans le tableau
	 * @return vrai si le trou peut être vidé
	 */
	@Override
	public boolean peutViderTrou(int trou) {
		return (super.getTrou(trou).getGraines() > 0);
	}

	/*
	 * peut semer dans le trou de départ
	 */
	@Override
	public boolean peutSemerDansTrouDeDépart() {
		return true;
	}
}
