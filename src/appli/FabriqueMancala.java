package appli;

import mancala.Awalé;
import mancala.Kakua;
import mancala.Owani;
import mancala.TamtamApachi;
import mancala.Wali;

public class FabriqueMancala {
	public static Mancala fabrique(int variante, int joueur, boolean varianteAwalé) {
		switch(variante) {
		case 1:
			return new Awalé(joueur, varianteAwalé);
		case 2:
			return new Kakua(joueur);
		case 3:
			return new Owani(joueur);
		case 4: 
			return new TamtamApachi(joueur);
		case 5:
			return new Wali(joueur);
		default:
			return new Awalé();
		}
	}
}
