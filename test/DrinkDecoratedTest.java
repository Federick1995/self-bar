package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Analcolico;
import model.Drink;
import model.DrinkWithAppetizerDec;
import model.DrinkWithSodaDec;
import model.Martini;

public class DrinkDecoratedTest {
	private static Drink ma = new Martini();
	private static Drink an = new Analcolico();

	@Test
	public void martiniWithSodaPriceTest() {
		double price = 6.50;
		Drink d = new DrinkWithSodaDec(ma);
		assertEquals(price, d.getPrice(), 0.001);
	}
	
	@Test
	public void martiniWithAppetizerPriceTest() {
		double price = 7.00;
		Drink d = new DrinkWithAppetizerDec(ma);
		assertEquals(price, d.getPrice(), 0.001);
	}
	
	@Test
	public void martiniWithSodaStringTest() {
		String desc = "Martini: 5,00 con soda (+1,50)";
		Drink d = new DrinkWithSodaDec(ma);
		assertEquals(desc, d.toString());
	}
	
	@Test
	public void martiniWithAppetizerStringTest() {
		String desc = "Martini: 5,00 con aperitivo (+2,00)";
		Drink d = new DrinkWithAppetizerDec(ma);
		assertEquals(desc, d.toString());
	}
	
	@Test
	public void analcolicoWithSodaPriceTest() {
		double price = 5.0;
		Drink d = new DrinkWithSodaDec(an);
		assertEquals(price, d.getPrice(), 0.001);
	}
	
	@Test
	public void analcolicoWithAppetizerPriceTest() {
		double price = 5.50;
		Drink d = new DrinkWithAppetizerDec(an);
		assertEquals(price, d.getPrice(), 0.001);
	}
	
	@Test
	public void analcolicoWithSodaStringTest() {
		String desc = "Analcolico: 3,50 con soda (+1,50)";
		Drink d = new DrinkWithSodaDec(an);
		assertEquals(desc, d.toString());
	}
	
	@Test
	public void analcolicoWithAppetizerStringTest() {
		String desc = "Analcolico: 3,50 con aperitivo (+2,00)";
		Drink d = new DrinkWithAppetizerDec(an);
		assertEquals(desc, d.toString());
	}

}
