package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Analcolico;
import model.Martini;
import model.Product;

public class DrinkBaseTest {
	private static Product ma = new Martini();
	private static Product an = new Analcolico();
	
	
	@Test
	public void martiniPriceTest() {
		double price = 5.00;
		assertEquals(price, ma.getPrice(), 0.001);
	}
	
	@Test
	public void martiniStringTest() {
		String desc = "Martini: 5,00";
		assertEquals(desc, ma.toString());
	}
	
	@Test
	public void analcolicoPriceTest() {
		double price = 3.5;
		assertEquals(price, an.getPrice(), 0.001);
	}
	
	@Test
	public void analcolicoStringTest() {
		String desc = "Analcolico: 3,50";
		assertEquals(desc, an.toString());
	}


}
