package trou;

public class Trou {
	private int graines;
	
	/*
	 * @param nbGraines nombre de graines dans un trou à sa création
	 */
	public Trou(int nbGraines) {
		this.graines = nbGraines;
	}
	
	/*
	 * @return le nombre de graines contenues dans le trou
	 */
	public int getGraines() {
		return this.graines;
	}
	
	/*
	 * ajoute une graine au trou
	 */
	public void ajouterGraine() {
		++this.graines;
	}
	
	/*
	 * retire une graine au trou
	 */
	public void enleverGraine() {
		assert(this.graines > 0);
		--this.graines;
	}
	
	/*
	 * vide le trou
	 */
	public void vider() {
		this.graines = 0;
	}
	
	/*
	 * @return vrai si le trou est vide
	 */
	public boolean estVide() {
		return this.graines == 0;
	}
	
	/*
	 * @return l'affichage du nombre de graines contenues dans le trou
	 */
	public String toString() {
		return Integer.toString(this.graines);
	}
}
