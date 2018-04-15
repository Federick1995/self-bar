package test;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import model.Coffe;
import model.CoffeArabica;
import model.CoffeWithMilkDec;
import model.Drink;
import model.DrinkWithSodaDec;
import model.Martini;
import model.Order;
import model.Product;

public class OrderTest {

	@Test
	public void orderBasePriceTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Order o = new Order();
		o.addProduct(c);
		o.addProduct(d);
		double tot = c.getPrice()+d.getPrice();
		assertEquals(tot, o.getTotalPrice(), 0.001);
	}
	
	@Test
	public void orderDecPriceTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Order o = new Order();
		c = new CoffeWithMilkDec(c);
		d = new DrinkWithSodaDec(d);
		o.addProduct(c);
		o.addProduct(d);
		double tot = c.getPrice()+d.getPrice();
		assertEquals(tot, o.getTotalPrice(), 0.001);
	}
	
	@Test
	public void orderBaseStringTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Order o = new Order();
		o.addProduct(c);
		o.addProduct(d);
		String desc = c.toString()+'\n'+d.toString()+'\n';
		assertEquals(desc, o.toString());
	}
	
	@Test
	public void orderDecStringTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Order o = new Order();
		c = new CoffeWithMilkDec(c);
		d = new DrinkWithSodaDec(d);
		o.addProduct(c);
		o.addProduct(d);
		String desc = c.toString()+'\n'+d.toString()+'\n';
		assertEquals(desc, o.toString());
	}
	
	@Test
	public void orderGetProductTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Order o = new Order();
		c = new CoffeWithMilkDec(c);
		d = new DrinkWithSodaDec(d);
		Stack<Product> s = new Stack<Product>();
		s.add(c);
		s.add(d);
		o.addProduct(c);
		o.addProduct(d);
		assertEquals(s, o.getProduct());
	}
	
	@Test
	public void orderGetNullListProductTest() {
		Order o = new Order();
		Stack<Product> l = new Stack<Product>();
		assertEquals(l,  o.getProduct());
	}
	
	

}
