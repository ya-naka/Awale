package mancala;

public class Owani extends AwaléStandard{

	public Owani() {
		this(0);
	}
	
	/*
	 * @param joueur numéro du joueur qui commence la partie
	 */
	public Owani(int joueur) {
		super(joueur);
	}
	
	/*
	 * enchaine les semis tant que le trou dans lequel le joueur aboutit est dans sa rangée
	 */
	public void semer() {
		do {
			super.semer();
			super.choisirTrou(super.getTrouFinSemis());
		}while(super.getTrouFinSemis()/(super.getNbTrous()/super.getNbJoueurs()) == super.getJoueurActif());
	}
	
	/*
	 * après un tour de plateau, ne sème que dans la rangée adversaire
	 * @param trou index du trou concerné dans le tableau
	 * @return vrai si le joueur peut semer dans le trou
	 */
	@Override
	public boolean peutSemer(int trou) {
		if(super.getTourComplet())
			return super.peutSemer(trou) && (trou/(super.getNbTrous()/super.getNbJoueurs()) == ((super.getJoueurActif()+1)%this.getNbJoueurs()));
		else
			return super.peutSemer(trou);
	}
	
	/*
	 * ne peut pas semer dans le trou de départ
	 */
	@Override
	public boolean peutSemerDansTrouDeDépart() {
		return false;
	}
}
