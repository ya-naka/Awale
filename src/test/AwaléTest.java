package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mancala.Awal�;

public class Awal�Test {
	@Test
	public void testNbGrainesDansRang�eDe() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.nbGrainesDansRang�eDe(0) == 24);
		assertTrue(jeu.nbGrainesDansRang�eDe(1) == 24);
		jeu.getTrou(0).vider();
		assertFalse(jeu.nbGrainesDansRang�eDe(0) == 24);
		assertTrue(jeu.nbGrainesDansRang�eDe(0) == 20);
	}
	
	@Test
	public void testScore() {
		Awal� jeu = new Awal�();
		jeu.ajouterGrainesRamass�es(2);
		jeu.actualiserScore();
		assertTrue(jeu.score(0) == 2);
		assertTrue(jeu.score(1) == 0);
	}
	
	@Test
	public void testNbTrouRemplisDansRang�eDe() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.nbTrouRemplisDansRang�eDe(0) == 6);
		assertTrue(jeu.nbTrouRemplisDansRang�eDe(1) == 6);
		jeu.getTrou(0).enleverGraine();
		assertTrue(jeu.nbTrouRemplisDansRang�eDe(0) == 6);
		jeu.getTrou(11).vider();
		assertFalse(jeu.nbTrouRemplisDansRang�eDe(1) == 6);
		assertTrue(jeu.nbTrouRemplisDansRang�eDe(1) == 5);
	}
	
	@Test
	public void testAjouterGrainesRamass�es() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.getGrainesRamass�es() == 0);
		jeu.ajouterGrainesRamass�es(0);
		assertTrue(jeu.getGrainesRamass�es() == 0);
		jeu.ajouterGrainesRamass�es(12);
		assertFalse(jeu.getGrainesRamass�es() == 0);
		assertTrue(jeu.getGrainesRamass�es() == 12);
		jeu.ajouterGrainesRamass�es(1);
		assertTrue(jeu.getGrainesRamass�es() == 13);
	}
	
	@Test
	public void testJoueurSuivant() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.getJoueurActif() == 0);
		jeu.ajouterGrainesRamass�es(2);
		assertTrue(jeu.score(0) == 0);
		jeu.joueurSuivant();
		assertTrue(jeu.getJoueurActif() == 1);
		assertTrue(jeu.getGrainesRamass�es() == 0);
		assertTrue(jeu.score(0) == 2);
	}
	
	@Test
	public void testActualiserScore() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.getJoueurActif() == 0);
		assertTrue(jeu.score(0) == 0);
		jeu.ajouterGrainesRamass�es(2);
		jeu.actualiserScore();
		assertTrue(jeu.score(0) == 2);
	}
	
	@Test
	public void testChoisirTrou2() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.getTrouD�partSemis() == 1);
		jeu.choisirTrou(0);
		assertTrue(jeu.getTrouD�partSemis() == 0);
	}
	
	@Test
	public void testPeutViderTrou() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.peutViderTrou(0));
		assertFalse(jeu.peutViderTrou(10));
		jeu.getTrou(0).vider();
		assertFalse(jeu.peutViderTrou(0));
		//ajoute 1 graine dans le trou
		jeu.getTrou(0).ajouterGraine();
		assertTrue(jeu.peutViderTrou(0));
		jeu.joueurSuivant();
		//ne peut semer qu'� partir d'un trou de sa rang�e
		assertFalse(jeu.peutViderTrou(1));
		assertTrue(jeu.peutViderTrou(10));
	}
	
	@Test
	public void testPeutSemer() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.peutSemer(2));
		assertTrue(jeu.peutSemer(11));
		assertFalse(jeu.peutSemer(jeu.getTrouD�partSemis()));
	}
	
	@Test
	public void testPeutRamasser() {
		Awal� jeu = new Awal�();
		assertFalse(jeu.peutRamasser(10));
		assertFalse(jeu.peutRamasser(4));
		jeu.getTrou(5).enleverGraine();
		assertFalse(jeu.peutRamasser(5));
		jeu.getTrou(8).enleverGraine();
		assertTrue(jeu.peutRamasser(8));
		jeu.getTrou(11).vider();
		assertFalse(jeu.peutRamasser(11));
	}
	
	@Test
	public void testPeutContinuerLaPartie() {
		Awal� jeu = new Awal�();
		assertFalse(jeu.partieFinie());
		//vide les 11 premiers trous du plateau
		for(int i = 0; i < jeu.getNbTrous()-1; ++i)
			jeu.getTrou(i).vider();
		jeu.peutContinuerLaPartie();
		assertFalse(jeu.partieFinie());
		jeu.getTrou(jeu.getNbTrous()-1).enleverGraine();
		jeu.peutContinuerLaPartie();
		assertFalse(jeu.partieFinie());
		//il ne reste que 2 graines dans le dernier trou
		jeu.getTrou(jeu.getNbTrous()-1).enleverGraine();
		jeu.peutContinuerLaPartie();
		assertTrue(jeu.partieFinie());
	}
	
	@Test
	public void testSemer() {
		//semer � partir du premier trou du plateau
		Awal� jeu = new Awal�();
		jeu.choisirTrou(0);
		jeu.semer();
		assertTrue(jeu.getTrou(0).estVide());
		for(int i = 1; i < 5; ++i)
			assertTrue(jeu.getTrou(i).getGraines() == 5);
		assertTrue(jeu.getTrou(jeu.getTrouFinSemis()).getGraines() == 5);
		assertFalse(jeu.getTrou(5).getGraines() == 5);
		
		//semer � partir du dernier trou du plateau
		jeu = new Awal�(1, false);
		jeu.choisirTrou(11);
		jeu.semer();
		assertTrue(jeu.getTrou(11).estVide());
		assertTrue(jeu.getTrou(0).getGraines() == 5);
		assertTrue(jeu.getTrou(1).getGraines() == 5);
		assertTrue(jeu.getTrou(2).getGraines() == 5);
		assertTrue(jeu.getTrou(3).getGraines() == 5);
		assertTrue(jeu.getTrou(jeu.getTrouFinSemis()).getGraines() == 5);
		assertFalse(jeu.getTrou(4).getGraines() == 5);
	}

	@Test
	public void testRamasser() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.getGrainesRamass�es() == 0);
		jeu.ramasser();
		assertTrue(jeu.getGrainesRamass�es() == 0);
		assertFalse(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
		jeu.getTrou(jeu.getTrouFinSemis()).enleverGraine();
		jeu.ramasser();
		assertTrue(jeu.getGrainesRamass�es() == 0);
		assertFalse(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
		jeu.joueurSuivant();
		jeu.ramasser();
		assertFalse(jeu.getGrainesRamass�es() == 0);
		assertTrue(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
	}
	
	@Test
	public void testTrouSuivant() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.trouSuivant(0) == 1);
		assertFalse(jeu.trouSuivant(11) == 12);
		assertTrue(jeu.trouSuivant(11) == 0);
	}
	
	@Test
	public void testTrouPr�c�dent() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.trouPr�c�dent(1) == 0);
		assertFalse(jeu.trouPr�c�dent(0) == -1);
		assertTrue(jeu.trouPr�c�dent(0) == 11);
	}
	
	@Test
	public void testSaisieValide() {
		Awal� jeu = new Awal�();
		assertTrue(jeu.saisieValide(0));
		assertTrue(jeu.saisieValide(6));
		assertTrue(jeu.saisieValide(12));
		assertFalse(jeu.saisieValide(-1));
		assertFalse(jeu.saisieValide(13));
	}

	@Test
	public void testChoisirTrou() {
		//ne peut choisir que dans sa propre rang�e
		Awal� jeu = new Awal�();
		//saisir 2
		jeu.choisirTrou();
		assertTrue(jeu.getTrouD�partSemis() == 1);
		assertFalse(jeu.partieFinie());
		
		jeu.joueurSuivant();
		//saisir 12
		jeu.choisirTrou();
		assertTrue(jeu.getTrouD�partSemis() == 11);
		assertFalse(jeu.partieFinie());
		
		//saisir 0
		jeu.choisirTrou();
		assertTrue(jeu.getTrouD�partSemis() == 11);
		assertTrue(jeu.partieFinie());
	}

	@Test
	public void testpeutSemerDansTrouDeD�part() {
		Awal� jeu = new Awal�(0, false);
		assertTrue(jeu.peutSemer(5));
		assertFalse(jeu.peutSemer(jeu.getTrouD�partSemis()));
	}
	
	@Test
	public void testRamasserAvant() {
		Awal� jeu = new Awal�(0, false);
		jeu.semer();
		jeu.getTrou(1).ajouterGraine();
		assertTrue(jeu.getTrouFinSemis() == 5);
		jeu.joueurSuivant();
		jeu.ramasserAvant();
		assertTrue(jeu.getTrou(4).getGraines() == 5);
		
		//enl�ve 2 graines aux trous d'index 5 � 2
		for(int i = 5; i >= 2; --i) {
			jeu.getTrou(i).enleverGraine();
			jeu.getTrou(i).enleverGraine();
		}
		assertTrue(jeu.getTrou(2).getGraines() == 3);
		assertTrue(jeu.getTrou(1).getGraines() == 1);
		jeu.ramasserAvant();
		assertTrue(jeu.getGrainesRamass�es() == 9);
		assertTrue(jeu.getTrou(5).getGraines() == 3);
		for(int i = 4; i >= 2; --i)
			assertTrue(jeu.getTrou(i).estVide());
		assertTrue(jeu.getTrou(1).getGraines() == 1);
	}
	
	@Test
	public void testRamasserApr�s() {
		Awal� jeu = new Awal�(0, false);
		jeu.choisirTrou(0);
		jeu.semer();
		assertTrue(jeu.getTrouFinSemis() == 4);
		assertTrue(jeu.getTrou(4).getGraines() == 5);
		jeu.joueurSuivant();
		jeu.ramasserApr�s();
		assertTrue(jeu.getTrou(5).getGraines() == 4);
		
		jeu.getTrou(5).enleverGraine();
		jeu.ramasserApr�s();
		assertTrue(jeu.getGrainesRamass�es() == 3);
		assertTrue(jeu.getTrou(5).estVide());
		assertFalse(jeu.getTrou(6).estVide());
		assertFalse(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
	}
	
	
}
