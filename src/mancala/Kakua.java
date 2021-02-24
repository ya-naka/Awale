package mancala;

public class Kakua extends AwaléStandard{

	public Kakua() {
		this(0);
	}
	
	/*
	 * @param joueur numéro du joueur qui commence la partie
	 */
	public Kakua(int joueur) {
		super(joueur);
	}

	/*
	 * enchaine les semis tant que le trou dans lequel le joueur aboutit n'est pas vide
	 * ramasse une graine pour chaque semis réalisé
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
	 * enlève une graine au début de chaque semis
	 */
	@Override
	public void semer() {
		super.getTrou(super.getTrouDépartSemis()).enleverGraine();
		super.semer();
	}
	
	/*
	 * gagne une graine à chaque semis réalisé
	 */
	@Override
	public void ramasser() {
		super.ajouterGrainesRamassées(1);
	}

	/*
	 * peut semer dans le trou de départ
	 */
	@Override
	public boolean peutSemerDansTrouDeDépart() {
		return true;
	}
	
	
}
