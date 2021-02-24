package mancala;

public class Wali extends Awal�Standard{

	public Wali() {
		this(0);
	}
	
	/*
	 * @param joueur num�ro du joueur qui commence la partie
	 */
	public Wali(int joueur) {
		super(joueur);
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
	
	/*
	 * retourne vrai s'il y a au moins 1 graine dans le trou
	 * @param trou index du trou concern� dans le tableau
	 * @return vrai si le trou peut �tre vid�
	 */
	@Override
	public boolean peutViderTrou(int trou) {
		return (super.getTrou(trou).getGraines() > 0);
	}

	/*
	 * peut semer dans le trou de d�part
	 */
	@Override
	public boolean peutSemerDansTrouDeD�part() {
		return true;
	}
}
