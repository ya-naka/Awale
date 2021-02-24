package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mancala.Wali;

public class WaliTest {

	@Test
	public void testPeutViderTrou() {
		Wali jeu = new Wali(0);
		assertTrue(jeu.peutViderTrou(0));
		assertTrue(jeu.peutViderTrou(10));
		jeu.getTrou(0).vider();
		assertFalse(jeu.peutViderTrou(0));
		jeu.getTrou(10).vider();
		assertFalse(jeu.peutViderTrou(10));
		//1 graine dans le trou
		jeu.getTrou(10).ajouterGraine();
		assertTrue(jeu.peutViderTrou(10));
	}
	
	@Test
	public void testChoisirTrou() {
		//peut choisir dans n'importe quelle rangée
		Wali jeu = new Wali(0);
		//saisir 11
		jeu.choisirTrou();
		assertTrue(jeu.getTrouDépartSemis() == 10);
		assertFalse(jeu.partieFinie());
		
		jeu.joueurSuivant();
		//saisir 1
		jeu.choisirTrou();
		assertTrue(jeu.getTrouDépartSemis() == 0);
		assertFalse(jeu.partieFinie());
				
		//saisir 0
		jeu.choisirTrou();
		assertTrue(jeu.getTrouDépartSemis() == 0);
		assertTrue(jeu.partieFinie());
	}
	
	@Test
	public void testScore() {
		Wali jeu = new Wali(0);
		jeu.ajouterGrainesRamassées(2);
		jeu.actualiserScore();
		assertTrue(jeu.score(0) == 2);
		assertTrue(jeu.score(1) == 0);
		//vide tous les trous du plateau
		for(int i = 0; i < jeu.getNbTrous(); ++i)
			jeu.getTrou(i).vider();
		//ajoute 2 graines dans le trou d'index 0
		jeu.getTrou(0).ajouterGraine();
		jeu.getTrou(0).ajouterGraine();
		jeu.peutContinuerLaPartie();
		assertTrue(jeu.score(0) == 4);
		assertTrue(jeu.score(1) == 0);
	}

}
