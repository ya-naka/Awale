package mancala;

public class TamtamApachi extends AwaléStandard{

	public TamtamApachi() {
		this(0);
	}
	
	/*
	 * @param joueur numéro du joueur qui commence la partie
	 */
	public TamtamApachi(int joueur) {
		super(joueur);
	}
	
	/*
	 * enchaine les semis tant que le trou dans lequel aboutit le joueur a au moins 2 graines
	 */
	public void semer() {
		do{
			super.semer();
			super.choisirTrou(super.getTrouFinSemis());
		}while(super.getTrou(super.getTrouFinSemis()).getGraines() >= 2);
	}

	/*
	 * ramasse les graines se trouvant dans le trou correspondant de la rangée
	 * adverse si c'est possible
	 */
	@Override
	public void ramasser() {
		if(this.peutRamasser(super.getTrouFinSemis())) {
			super.ajouterGrainesRamassées(super.getTrou((this.getNbTrous()-1)-super.getTrouFinSemis()).getGraines());
			super.getTrou((this.getNbTrous()-1)-super.getTrouFinSemis()).vider();
		}
	}

	/*
	 * @return vrai si le dernier trou du semis se trouve dans sa propre rangée
	 * et si le trou correspondant dans la rangée d'en face a au moins 1 graine
	 */
	@Override
	public boolean peutRamasser(int trou) {
		return (super.getJoueurActif() == super.getTrouFinSemis()/(this.getNbTrous()/this.getNbJoueurs())) 
				&& super.getTrou((this.getNbTrous()-1)-super.getTrouFinSemis()).getGraines() >= 1
				&& super.getTrou(super.getTrouFinSemis()).getGraines() == 1;
	}

	/*
	 * peut semer dans le trou de départ
	 */
	@Override
	public boolean peutSemerDansTrouDeDépart() {
		return true;
	}
}
