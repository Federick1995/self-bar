package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Coffe;
import model.CoffeArabica;
import model.CoffeRobusta;
import model.CoffeWithCreamDec;
import model.CoffeWithMilkDec;
import model.CoffeWithSanbucaDec;

public class CoffeDecoratedTest {
	private static Coffe ca = new CoffeArabica();
	private static Coffe cr = new CoffeRobusta();
	
	@Test
	public void arabicaCreamPriceTest() {
		double price = 1.50;
		Coffe c = new CoffeWithCreamDec(ca);
		assertEquals(price, c.getPrice(), 0.001);
	}
	
	@Test
	public void arabicaMilkPriceTest() {
		double price = 1.30;
		Coffe c = new CoffeWithMilkDec(ca);
		assertEquals(price, c.getPrice(), 0.001);
	}
	
	@Test
	public void arabicaSanbucaPriceTest() {
		double price = 2.50;
		Coffe c = new CoffeWithSanbucaDec(ca);
		assertEquals(price, c.getPrice(), 0.001);
	}
	
	@Test
	public void arabicaCreamStringTest() {
		String desc = "Coffe arabica: 1,00 con panna (+0,50)";
		Coffe c = new CoffeWithCreamDec(ca);
		assertEquals(desc, c.toString());
	}
	
	@Test
	public void arabicaMilkStringTest() {
		String desc = "Coffe arabica: 1,00 con latte (+0,30)";
		Coffe c = new CoffeWithMilkDec(ca);
		assertEquals(desc, c.toString());
	}
	
	@Test
	public void arabicaSanbucaStringTest() {
		String desc = "Coffe arabica: 1,00 con sambuca (+1,50)";
		Coffe c = new CoffeWithSanbucaDec(ca);
		assertEquals(desc, c.toString());
	}
	
	@Test
	public void robustaCreamPriceTest() {
		double price = 1.60;
		Coffe c = new CoffeWithCreamDec(cr);
		assertEquals(price, c.getPrice(), 0.001);
	}
	
	@Test
	public void robustaMilkPriceTest() {
		double price = 1.40;
		Coffe c = new CoffeWithMilkDec(cr);
		assertEquals(price, c.getPrice(), 0.001);
	}
	
	@Test
	public void robustaSanbucaPriceTest() {
		double price = 2.60;
		Coffe c = new CoffeWithSanbucaDec(cr);
		assertEquals(price, c.getPrice(), 0.001);
	}
	
	@Test
	public void robustaCreamStringTest() {
		String desc = "Coffe robusta: 1,10 con panna (+0,50)";
		Coffe c = new CoffeWithCreamDec(cr);
		assertEquals(desc, c.toString());
	}
	
	@Test
	public void robustaMilkStringTest() {
		String desc = "Coffe robusta: 1,10 con latte (+0,30)";
		Coffe c = new CoffeWithMilkDec(cr);
		assertEquals(desc, c.toString());
	}
	
	@Test
	public void robustaSanbucaStringTest() {
		String desc = "Coffe robusta: 1,10 con sambuca (+1,50)";
		Coffe c = new CoffeWithSanbucaDec(cr);
		assertEquals(desc, c.toString());
	}

}
