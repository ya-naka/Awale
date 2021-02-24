package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mancala.Awalé;
import mancala.Kakua;

public class KakuaTest {

	@Test
	public void testRamasser() {
		Awalé jeu = new Awalé(0, false);
		jeu.ramasser();
		for(int i = 0; i < jeu.getNbTrous(); ++i)
			assertTrue(jeu.getTrou(i).getGraines() == 4);
		jeu.choisirTrou(0);
		jeu.semer();
		jeu.getTrou(1).ajouterGraine();
		//ajoute 3 graines dans le trou d'index 4
		jeu.getTrou(4).enleverGraine();
		jeu.getTrou(4).enleverGraine();
		jeu.getTrou(4).enleverGraine();
		assertTrue(jeu.getTrou(4).getGraines() == 2);
		jeu.joueurSuivant();
		jeu.ramasser();
		assertTrue(jeu.getGrainesRamassées() == 2);
		assertTrue(jeu.getTrou(4).estVide());
		//ajoute 3 graines dans le trou d'index 4
		jeu.getTrou(4).ajouterGraine();
		jeu.getTrou(4).ajouterGraine();
		jeu.getTrou(4).ajouterGraine();
		//enlève 2 graines dans les trous d'index 2 et 3
		for(int i = 3; i >= 2; --i) {
			jeu.getTrou(i).enleverGraine();
			jeu.getTrou(i).enleverGraine();
		}
		jeu.getTrou(5).enleverGraine();
		for(int i = 2; i <= 5; ++i)
			assertTrue(jeu.getTrou(i).getGraines() == 3);
		//réinitialise le nombre de graines ramassées
		jeu.joueurSuivant();
		jeu.joueurSuivant();
		jeu.ramasser();
		for(int i = 2; i <= 5; ++i)
			assertTrue(jeu.getTrou(i).estVide());
		assertFalse(jeu.getTrou(1).estVide());
		assertFalse(jeu.getTrou(6).estVide());
	}
	
	@Test
	public void testPeutRamasser() {
		Awalé jeu = new Awalé(0, false);
		assertFalse(jeu.peutRamasser(0));
		assertFalse(jeu.peutRamasser(10));
		jeu.getTrou(10).enleverGraine();
		assertTrue(jeu.peutRamasser(10));
		for(int i = 11; i > 7; --i)
			jeu.getTrou(i).vider();
		jeu.getTrou(7).enleverGraine();
		assertTrue(jeu.peutRamasser(7));
		jeu.getTrou(6).vider();
		assertFalse(jeu.peutRamasser(7));
	}

	@Test
	public void testSemer() {
		Kakua jeu = new Kakua(0);
		jeu.semer();
		assertTrue(jeu.getTrou(jeu.getTrouDépartSemis()).estVide());
		for(int i = 2; i < 5; ++i) 
			assertTrue(jeu.getTrou(i).getGraines() == 5);
		assertFalse(jeu.getTrou(5).getGraines() == 5);
		assertTrue(jeu.getTrou(5).getGraines() == 4);
	}
	
	@Test
	public void testScore() {
		Awalé jeu = new Awalé(0, false);
		assertTrue(jeu.score(0) == 0);
		assertTrue(jeu.score(1) == 0);
		for(int i = 0; i < jeu.getNbTrous(); ++i)
			jeu.getTrou(i).vider();
		jeu.getTrou(0).ajouterGraine();;
		jeu.getTrou(0).ajouterGraine();
		jeu.peutContinuerLaPartie();
		System.out.println(jeu.score(0));
		assertTrue(jeu.score(0) == 2);
		assertTrue(jeu.score(1) == 0);
	}
}
