package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Product;
import model.CoffeArabica;
import model.CoffeRobusta;

public class CoffeBaseTest {
	
	private static Product ca = new CoffeArabica();
	private static Product cr = new CoffeRobusta();

	@Test
	public void coffeArabicaPriceTest() {
		double price = 1.00;
		assertEquals(price, ca.getPrice(), 0.001);
	}
	
	@Test
	public void coffeArabicaStringTest() {
		String desc = "Coffe arabica: 1,00";
		assertEquals(desc, ca.toString());
	}
	
	@Test
	public void coffeRobustaPriceTest() {
		double price = 1.1;
		assertEquals(price, cr.getPrice(), 0.001);
	}
	
	@Test
	public void coffeRobustaStringTest() {
		String desc = "Coffe robusta: 1,10";
		assertEquals(desc, cr.toString());
	}

}
