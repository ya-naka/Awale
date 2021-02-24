package mancala;

public class Kakua extends Awal�Standard{

	public Kakua() {
		this(0);
	}
	
	/*
	 * @param joueur num�ro du joueur qui commence la partie
	 */
	public Kakua(int joueur) {
		super(joueur);
	}

	/*
	 * enchaine les semis tant que le trou dans lequel le joueur aboutit n'est pas vide
	 * ramasse une graine pour chaque semis r�alis�
	 */
	@Override
	public void jouer() {
		do{
			this.semer();
			this.ramasser();
			super.choisirTrou(super.trouSuivant(super.getTrouFinSemis()));
		}while(!super.getTrou(super.trouSuivant(super.getTrouFinSemis())).estVide());
	}
	
	/*
	 * enl�ve une graine au d�but de chaque semis
	 */
	@Override
	public void semer() {
		super.getTrou(super.getTrouD�partSemis()).enleverGraine();
		super.semer();
	}
	
	/*
	 * gagne une graine � chaque semis r�alis�
	 */
	@Override
	public void ramasser() {
		super.ajouterGrainesRamass�es(1);
	}

	/*
	 * peut semer dans le trou de d�part
	 */
	@Override
	public boolean peutSemerDansTrouDeD�part() {
		return true;
	}
	
	
}
