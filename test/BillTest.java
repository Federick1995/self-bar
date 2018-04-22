package test;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import model.Analcolico;
import model.Bill;
import model.Coffe;
import model.CoffeArabica;
import model.CoffeRobusta;
import model.CoffeWithCreamDec;
import model.CoffeWithMilkDec;
import model.Drink;
import model.DrinkWithSodaDec;
import model.Martini;
import model.PaymentCash;
import model.PaymentCreditCard;
import model.PaymentDebitCard;
import model.PaymentStrategy;
import model.Product;


public class BillTest {
	private static Coffe ca = new CoffeArabica();
	private static Coffe cr = new CoffeRobusta();
	private static Drink m = new Martini();
	private static Drink a = new Analcolico();
		
	@Test
	public void billBaseStringTest() {
		Coffe c = new CoffeWithMilkDec(ca);
		Drink d = new DrinkWithSodaDec(m);
		Bill b = new Bill();
		b.addProduct(c);
		b.addProduct(d);
		b.confirmOrder();
		String desc = "Ordine 1:\n"+c.toString()+'\n'+d.toString()+'\n'+
				"------------------\n"+"TOTALE: "+String.format("%.2f", b.getTotalPrice());
		assertEquals(desc, b.toString());
	}
	
	@Test
	public void billDecPriceTest() {
		Coffe c = new CoffeWithMilkDec(ca);
		Drink d =  new DrinkWithSodaDec(m);
		Bill b = new Bill();
		b.addProduct(c);
		b.addProduct(d);
		b.confirmOrder();
		double tot = c.getPrice()+d.getPrice();
		assertEquals(tot, b.getTotalPrice(), 0.001);
	}
	
	@Test
	public void billRemovePriceTest() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.addProduct(m);
		b.removeProduct(m);
		b.removeProduct(ca);
		b.confirmOrder();
		assertEquals(0.0, b.getTotalPrice(), 0.001);
	}
	
	//remove acts only on actual order (order created after confirm..)
	@Test(expected = NullPointerException.class)
	public void billRemoveAfterConfirmPriceTest() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.addProduct(m);
		b.confirmOrder();
		b.removeProduct(m);
	}
	
	@Test
	public void billRemoveAfterAddConfirmPriceTest() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.addProduct(m);
		b.confirmOrder();
		double total = m.getPrice()+ca.getPrice();
		Coffe cr = new CoffeWithCreamDec(ca);
		b.addProduct(cr);
		b.removeProduct(cr);
		assertEquals(total, b.getTotalPrice(), 0.001);
	}
	
	@Test
	public void billGetProductNullTest() {
		Bill b = new Bill();
		assertEquals(null, b.getProduct());
	}
	
	@Test
	public void billGetProdutAfterAddTest() {
		Stack<Product> l = new Stack<Product>();
		l.add(m);
		l.add(ca);
		Bill b = new Bill();
		b.addProduct(a);
		b.confirmOrder();
		b.addProduct(m);
		b.addProduct(ca);
		assertEquals(l, b.getProduct());
	}
	
	@Test(expected = NullPointerException.class)
	public void billRemoveNullProduct() {
		Stack<Product> l = new Stack<Product>();
		Bill b = new Bill();
		b.addProduct(m);
		b.confirmOrder();
		b.addProduct(cr);
		l.add(cr);
		b.removeProduct(null);
	}
	
	@Test
	public void billAddAfterConfirmDescTest() {
		Coffe c = new CoffeWithMilkDec(ca);
		Drink d = new DrinkWithSodaDec(m);
		Bill b = new Bill();
		b.addProduct(c);
		b.addProduct(d);
		b.confirmOrder();
		b.addProduct(cr);
		String desc = "Ordine 1:\n"+c.toString()+'\n'+d.toString()+'\n'+"------------------\n"+"Ordine 2:\n"+
				cr.toString()+'\n'+"------------------\n"+"TOTALE: "+String.format("%.2f", b.getTotalPrice());
		assertEquals(desc, b.toString());
	}
	
	@Test
	public void billPayWithCashDescTest() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.addProduct(cr);
		b.confirmOrder();
		PaymentStrategy ps = new PaymentCash();
		b.setPaymentStrategy(ps);
		String desc = "Ordine 1:\n"+ca.toString()+'\n'+cr.toString()+'\n'+"------------------\n"+
			"TOTALE: "+String.format("%.2f", b.getTotalPrice())+
				'\n'+ps.pay(b.getTotalPrice());
		assertEquals(desc, b.toString());	
	}
	
	@Test
	public void billPayWithCreditCardDescTest() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.addProduct(cr);
		b.confirmOrder();
		PaymentStrategy ps = new PaymentCreditCard();
		b.setPaymentStrategy(ps);
		String desc = "Ordine 1:\n"+ca.toString()+'\n'+cr.toString()+'\n'+"------------------\n"+
			"TOTALE: "+String.format("%.2f", b.getTotalPrice())+
				'\n'+ps.pay(b.getTotalPrice());
		assertEquals(desc, b.toString());	
	}
	
	@Test
	public void billPayWithDebitCardDescTest() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.addProduct(cr);
		b.confirmOrder();
		PaymentStrategy ps = new PaymentDebitCard();
		b.setPaymentStrategy(ps);
		String desc = "Ordine 1:\n"+ca.toString()+'\n'+cr.toString()+'\n'+"------------------\n"+
			"TOTALE: "+String.format("%.2f", b.getTotalPrice())+
				'\n'+ps.pay(b.getTotalPrice());
		assertEquals(desc, b.toString());	
	}
	
	@Test
	public void billCanPayEmptyBill() {
		Bill b = new Bill();
		assertEquals(false, b.canPay());
	}
	
	@Test
	public void billCanPayTest() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.confirmOrder();
		assertEquals(true, b.canPay());
	}
	
	@Test
	public void billCanPayAfterAddTest() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.confirmOrder();
		b.addProduct(new Analcolico());
		assertEquals(false, b.canPay());
	}
	
	@Test
	public void billDescAfterPrint() {
		Bill b = new Bill();
		b.addProduct(ca);
		b.confirmOrder();
		b.clear();
		assertEquals("", b.toString());
	}
	
	@Test
	public void orderEmptyTest() {
		Bill b = new Bill();
		assertEquals(false, b.orderEmpty());
	}
	
	@Test
	public void orderEmptyAfterAddTest() {
		Bill b = new Bill();
		b.addProduct(new CoffeArabica());
		assertEquals(false, b.orderEmpty());
	}
	

}
