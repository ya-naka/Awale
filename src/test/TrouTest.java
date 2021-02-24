package test;

import static org.junit.Assert.*;

import org.junit.Test;

import trou.Trou;

public class TrouTest {

	@Test
	public void testGetGraines() {
		Trou t = new Trou(5);
		assertTrue(t.getGraines() == 5);
	}
	
	@Test
	public void testAjouterGraine() {
		Trou t = new Trou(5);
		assertTrue(t.getGraines() == 5);
		t.ajouterGraine();
		assertTrue(t.getGraines() == 6);
	}
	
	@Test
	public void testEnleverGraine() {
		Trou t = new Trou(5);
		assertTrue(t.getGraines() == 5);
		t.enleverGraine();
		assertTrue(t.getGraines() == 4);
	}
	
	@Test
	public void testVider() {
		Trou t = new Trou(5);
		assertTrue(t.getGraines() == 5);
		t.vider();
		assertTrue(t.getGraines() == 0);
	}
	
	@Test
	public void testEstVide() {
		Trou t = new Trou(5);
		assertFalse(t.estVide());
		t.vider();
		assertTrue(t.estVide());
		t.ajouterGraine();
		assertFalse(t.estVide());
	}

}
