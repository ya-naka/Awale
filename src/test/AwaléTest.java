package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mancala.Awalé;

public class AwaléTest {
	@Test
	public void testNbGrainesDansRangéeDe() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.nbGrainesDansRangéeDe(0) == 24);
		assertTrue(jeu.nbGrainesDansRangéeDe(1) == 24);
		jeu.getTrou(0).vider();
		assertFalse(jeu.nbGrainesDansRangéeDe(0) == 24);
		assertTrue(jeu.nbGrainesDansRangéeDe(0) == 20);
	}
	
	@Test
	public void testScore() {
		Awalé jeu = new Awalé();
		jeu.ajouterGrainesRamassées(2);
		jeu.actualiserScore();
		assertTrue(jeu.score(0) == 2);
		assertTrue(jeu.score(1) == 0);
	}
	
	@Test
	public void testNbTrouRemplisDansRangéeDe() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.nbTrouRemplisDansRangéeDe(0) == 6);
		assertTrue(jeu.nbTrouRemplisDansRangéeDe(1) == 6);
		jeu.getTrou(0).enleverGraine();
		assertTrue(jeu.nbTrouRemplisDansRangéeDe(0) == 6);
		jeu.getTrou(11).vider();
		assertFalse(jeu.nbTrouRemplisDansRangéeDe(1) == 6);
		assertTrue(jeu.nbTrouRemplisDansRangéeDe(1) == 5);
	}
	
	@Test
	public void testAjouterGrainesRamassées() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.getGrainesRamassées() == 0);
		jeu.ajouterGrainesRamassées(0);
		assertTrue(jeu.getGrainesRamassées() == 0);
		jeu.ajouterGrainesRamassées(12);
		assertFalse(jeu.getGrainesRamassées() == 0);
		assertTrue(jeu.getGrainesRamassées() == 12);
		jeu.ajouterGrainesRamassées(1);
		assertTrue(jeu.getGrainesRamassées() == 13);
	}
	
	@Test
	public void testJoueurSuivant() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.getJoueurActif() == 0);
		jeu.ajouterGrainesRamassées(2);
		assertTrue(jeu.score(0) == 0);
		jeu.joueurSuivant();
		assertTrue(jeu.getJoueurActif() == 1);
		assertTrue(jeu.getGrainesRamassées() == 0);
		assertTrue(jeu.score(0) == 2);
	}
	
	@Test
	public void testActualiserScore() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.getJoueurActif() == 0);
		assertTrue(jeu.score(0) == 0);
		jeu.ajouterGrainesRamassées(2);
		jeu.actualiserScore();
		assertTrue(jeu.score(0) == 2);
	}
	
	@Test
	public void testChoisirTrou2() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.getTrouDépartSemis() == 1);
		jeu.choisirTrou(0);
		assertTrue(jeu.getTrouDépartSemis() == 0);
	}
	
	@Test
	public void testPeutViderTrou() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.peutViderTrou(0));
		assertFalse(jeu.peutViderTrou(10));
		jeu.getTrou(0).vider();
		assertFalse(jeu.peutViderTrou(0));
		//ajoute 1 graine dans le trou
		jeu.getTrou(0).ajouterGraine();
		assertTrue(jeu.peutViderTrou(0));
		jeu.joueurSuivant();
		//ne peut semer qu'à partir d'un trou de sa rangée
		assertFalse(jeu.peutViderTrou(1));
		assertTrue(jeu.peutViderTrou(10));
	}
	
	@Test
	public void testPeutSemer() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.peutSemer(2));
		assertTrue(jeu.peutSemer(11));
		assertFalse(jeu.peutSemer(jeu.getTrouDépartSemis()));
	}
	
	@Test
	public void testPeutRamasser() {
		Awalé jeu = new Awalé();
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
		Awalé jeu = new Awalé();
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
		//semer à partir du premier trou du plateau
		Awalé jeu = new Awalé();
		jeu.choisirTrou(0);
		jeu.semer();
		assertTrue(jeu.getTrou(0).estVide());
		for(int i = 1; i < 5; ++i)
			assertTrue(jeu.getTrou(i).getGraines() == 5);
		assertTrue(jeu.getTrou(jeu.getTrouFinSemis()).getGraines() == 5);
		assertFalse(jeu.getTrou(5).getGraines() == 5);
		
		//semer à partir du dernier trou du plateau
		jeu = new Awalé(1, false);
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
		Awalé jeu = new Awalé();
		assertTrue(jeu.getGrainesRamassées() == 0);
		jeu.ramasser();
		assertTrue(jeu.getGrainesRamassées() == 0);
		assertFalse(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
		jeu.getTrou(jeu.getTrouFinSemis()).enleverGraine();
		jeu.ramasser();
		assertTrue(jeu.getGrainesRamassées() == 0);
		assertFalse(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
		jeu.joueurSuivant();
		jeu.ramasser();
		assertFalse(jeu.getGrainesRamassées() == 0);
		assertTrue(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
	}
	
	@Test
	public void testTrouSuivant() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.trouSuivant(0) == 1);
		assertFalse(jeu.trouSuivant(11) == 12);
		assertTrue(jeu.trouSuivant(11) == 0);
	}
	
	@Test
	public void testTrouPrécédent() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.trouPrécédent(1) == 0);
		assertFalse(jeu.trouPrécédent(0) == -1);
		assertTrue(jeu.trouPrécédent(0) == 11);
	}
	
	@Test
	public void testSaisieValide() {
		Awalé jeu = new Awalé();
		assertTrue(jeu.saisieValide(0));
		assertTrue(jeu.saisieValide(6));
		assertTrue(jeu.saisieValide(12));
		assertFalse(jeu.saisieValide(-1));
		assertFalse(jeu.saisieValide(13));
	}

	@Test
	public void testChoisirTrou() {
		//ne peut choisir que dans sa propre rangée
		Awalé jeu = new Awalé();
		//saisir 2
		jeu.choisirTrou();
		assertTrue(jeu.getTrouDépartSemis() == 1);
		assertFalse(jeu.partieFinie());
		
		jeu.joueurSuivant();
		//saisir 12
		jeu.choisirTrou();
		assertTrue(jeu.getTrouDépartSemis() == 11);
		assertFalse(jeu.partieFinie());
		
		//saisir 0
		jeu.choisirTrou();
		assertTrue(jeu.getTrouDépartSemis() == 11);
		assertTrue(jeu.partieFinie());
	}

	@Test
	public void testpeutSemerDansTrouDeDépart() {
		Awalé jeu = new Awalé(0, false);
		assertTrue(jeu.peutSemer(5));
		assertFalse(jeu.peutSemer(jeu.getTrouDépartSemis()));
	}
	
	@Test
	public void testRamasserAvant() {
		Awalé jeu = new Awalé(0, false);
		jeu.semer();
		jeu.getTrou(1).ajouterGraine();
		assertTrue(jeu.getTrouFinSemis() == 5);
		jeu.joueurSuivant();
		jeu.ramasserAvant();
		assertTrue(jeu.getTrou(4).getGraines() == 5);
		
		//enlève 2 graines aux trous d'index 5 à 2
		for(int i = 5; i >= 2; --i) {
			jeu.getTrou(i).enleverGraine();
			jeu.getTrou(i).enleverGraine();
		}
		assertTrue(jeu.getTrou(2).getGraines() == 3);
		assertTrue(jeu.getTrou(1).getGraines() == 1);
		jeu.ramasserAvant();
		assertTrue(jeu.getGrainesRamassées() == 9);
		assertTrue(jeu.getTrou(5).getGraines() == 3);
		for(int i = 4; i >= 2; --i)
			assertTrue(jeu.getTrou(i).estVide());
		assertTrue(jeu.getTrou(1).getGraines() == 1);
	}
	
	@Test
	public void testRamasserAprès() {
		Awalé jeu = new Awalé(0, false);
		jeu.choisirTrou(0);
		jeu.semer();
		assertTrue(jeu.getTrouFinSemis() == 4);
		assertTrue(jeu.getTrou(4).getGraines() == 5);
		jeu.joueurSuivant();
		jeu.ramasserAprès();
		assertTrue(jeu.getTrou(5).getGraines() == 4);
		
		jeu.getTrou(5).enleverGraine();
		jeu.ramasserAprès();
		assertTrue(jeu.getGrainesRamassées() == 3);
		assertTrue(jeu.getTrou(5).estVide());
		assertFalse(jeu.getTrou(6).estVide());
		assertFalse(jeu.getTrou(jeu.getTrouFinSemis()).estVide());
	}
	
	
}
