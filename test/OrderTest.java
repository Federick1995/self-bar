package test;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import model.Analcolico;
import model.Coffe;
import model.CoffeArabica;
import model.CoffeRobusta;
import model.CoffeWithCreamDec;
import model.CoffeWithMilkDec;
import model.Drink;
import model.DrinkWithSodaDec;
import model.Martini;
import model.Order;
import model.Product;

public class OrderTest {
	private static Coffe ca = new CoffeArabica();
	private static Coffe cr = new CoffeRobusta();
	private static Drink m = new Martini();
	private static Drink a = new Analcolico();

	@Test
	public void orderBasePriceTest() {
		Order o = new Order();
		o.addProduct(ca);
		o.addProduct(m);
		double tot = ca.getPrice()+m.getPrice();
		assertEquals(tot, o.getTotalPrice(), 0.001);
	}
	
	@Test
	public void orderDecPriceTest() {
		Coffe c = new CoffeWithMilkDec(ca);
		Drink d = new DrinkWithSodaDec(m);
		Order o = new Order();
		o.addProduct(c);
		o.addProduct(d);
		double tot = c.getPrice()+d.getPrice();
		assertEquals(tot, o.getTotalPrice(), 0.001);
	}
	
	@Test
	public void orderBaseStringTest() {
		Order o = new Order();
		o.addProduct(ca);
		o.addProduct(m);
		String desc = ca.toString()+'\n'+m.toString()+'\n';
		assertEquals(desc, o.toString());
	}
	
	@Test
	public void orderDecStringTest() {
		Coffe c = new CoffeWithCreamDec(cr);
		Drink d = new DrinkWithSodaDec(a);
		Order o = new Order();
		o.addProduct(c);
		o.addProduct(d);
		String desc = c.toString()+'\n'+d.toString()+'\n';
		assertEquals(desc, o.toString());
	}
	
	@Test
	public void orderGetProductTest() {
		Coffe c = new CoffeWithMilkDec(ca);
		Drink d = new DrinkWithSodaDec(m);
		Order o = new Order();
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
	
	//missing orderEmpty
	@Test
	public void emptyOrder() {
		Order o = new Order();
		assertEquals(true, o.orderEmpty());
	}
	
	@Test
	public void notEmptyOrder() {
		Order o = new Order();
		o.addProduct(a);
		assertEquals(false, o.orderEmpty());
	}

}
