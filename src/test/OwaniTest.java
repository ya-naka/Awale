package test;

import static org.junit.Assert.*;

import org.junit.Test;

import mancala.Owani;

public class OwaniTest {

	@Test
	public void testPeutSemerDansTrouDeDépart() {
		Owani jeu = new Owani(0);
		assertFalse(jeu.peutSemer(jeu.getTrouDépartSemis()));
		assertTrue(jeu.peutSemer(10));
	}
	
	@Test
	public void testPeutSemer() {
		Owani jeu = new Owani(0);
		assertFalse(jeu.getTourComplet());
		assertTrue(jeu.peutSemer(2));
		assertFalse(jeu.peutSemer(jeu.getTrouDépartSemis()));
		assertTrue(jeu.peutSemer(10));
		//ajoute 8 graines dans le trou de départ du prochain semis (total = 12)
		for(int i = 1; i < 9; ++i)
			jeu.getTrou(jeu.getTrouDépartSemis()).ajouterGraine();
		jeu.semer();
		assertTrue(jeu.getTourComplet());
		assertFalse(jeu.peutSemer(4));
		assertTrue(jeu.getTrou(1).estVide());
		assertTrue(jeu.getTrou(0).getGraines() == 5);
		assertTrue(jeu.getTrou(2).getGraines() == 5);
		assertTrue(jeu.getTrou(6).getGraines() == 6);
	}
	
	@Test
	public void testSemer() {
		Owani jeu = new Owani(0);
		jeu.choisirTrou(5);
		jeu.semer();
		assertTrue(jeu.getTrouFinSemis() == 9);
		assertTrue(jeu.getTrou(5).estVide());
		assertTrue(jeu.getTrou(jeu.getTrouFinSemis()).getGraines() == 5);
		assertFalse(jeu.getTrou(10).getGraines() == 5);
		//ajoute 4 graines dans le trou d'index 9
		for(int i = 1; i < 5; ++i)
			jeu.getTrou(9).ajouterGraine();
		assertTrue(jeu.getTrou(9).getGraines() == 9);
		jeu.joueurSuivant();
		jeu.choisirTrou(9);
		jeu.semer();
		assertTrue(jeu.getTrouFinSemis() == 0);
		assertTrue(jeu.getTrou(9).getGraines() == 1);
	}
}
