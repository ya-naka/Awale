package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mancala.TamtamApachi;

public class TamtamApachiTest {

	@Test
	public void testPeutRamasser() {
		TamtamApachi jeu = new TamtamApachi(0);
		assertFalse(jeu.peutRamasser(0));
		//enlève 3 graines
		for(int i = 1; i < 4; ++i)
			jeu.getTrou(0).enleverGraine();
		assertTrue(jeu.peutRamasser(0));
		jeu.getTrou(11).vider();
		assertFalse(jeu.peutRamasser(0));
	}
	
	@Test
	public void testRamasser() {
		TamtamApachi jeu = new TamtamApachi(0);
		jeu.ramasser();
		assertTrue(jeu.getGrainesRamassées() == 0);
		assertFalse(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
		//enlève 3 graines
		for (int i = 1; i < 4; ++i)
			jeu.getTrou(jeu.getTrouFinSemis()).enleverGraine();
		assertTrue(jeu.getTrou(jeu.getTrouFinSemis()).getGraines() == 1);
		jeu.ramasser();
		assertTrue(jeu.getGrainesRamassées() == 4);
		assertTrue(jeu.getTrou(11).estVide());
	}
}
