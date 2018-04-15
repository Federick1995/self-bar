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
	
	
//	@Test
//	public void billEmptyOrderDescTest() {
//		Bill b = new Bill();
//		String desc = "Ordine 1:\n"+"------------------\n"+"TOTAL BILL: 0,00";
//		assertEquals(desc, b.toString());
//	}
	
	
	@Test
	public void billBaseStringTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Bill b = new Bill();
		c = new CoffeWithMilkDec(c);
		d = new DrinkWithSodaDec(d);
		b.addProduct(c);
		b.addProduct(d);
		b.confirmOrder();
		String desc = "Ordine 1:\n"+c.toString()+'\n'+d.toString()+'\n'+
				"------------------\n"+"TOTALE: "+String.format("%.2f", b.getTotalPrice());
		assertEquals(desc, b.toString());
	}
	
	@Test
	public void billDecPriceTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Bill b = new Bill();
		c = new CoffeWithMilkDec(c);
		d = new DrinkWithSodaDec(d);
		b.addProduct(c);
		b.addProduct(d);
		b.confirmOrder();
		double tot = c.getPrice()+d.getPrice();
		assertEquals(tot, b.getTotalPrice(), 0.001);
	}
	
	@Test
	public void billRemovePriceTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Bill b = new Bill();
		b.addProduct(c);
		b.addProduct(d);
		b.removeProduct(d);
		b.removeProduct(c);
		b.confirmOrder();
		assertEquals(0.0, b.getTotalPrice(), 0.001);
	}
	
	//remove act only on actual order (order created after confirm..)
	@Test(expected = NullPointerException.class)
	public void billRemoveAfterConfirmPriceTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Bill b = new Bill();
		b.addProduct(c);
		b.addProduct(d);
		b.confirmOrder();
		b.removeProduct(d);
	}
	
	@Test
	public void billRemoveAfterAddConfirmPriceTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Bill b = new Bill();
		b.addProduct(c);
		b.addProduct(d);
		b.confirmOrder();
		double total = d.getPrice()+c.getPrice();
		Coffe cr = new CoffeWithCreamDec(new CoffeArabica());
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
		Drink m = new Martini();
		Coffe c = new CoffeArabica();
		Drink a = new Analcolico();
		Stack<Product> l = new Stack<Product>();
		l.add(m);
		l.add(c);
		Bill b = new Bill();
		b.addProduct(a);
		b.confirmOrder();
		b.addProduct(m);
		b.addProduct(c);
		assertEquals(l, b.getProduct());
	}
	
	@Test(expected = NullPointerException.class)
	public void billRemoveNullProduct() {
		Drink m = new Martini();
		Coffe c = new CoffeRobusta();
		Stack<Product> l = new Stack<Product>();
		Bill b = new Bill();
		b.addProduct(m);
		b.confirmOrder();
		b.addProduct(c);
		l.add(c);
		b.removeProduct(null);
	}
	
	@Test
	public void billAddAfterConfirmDescTest() {
		Coffe c = new CoffeArabica();
		Drink d = new Martini();
		Bill b = new Bill();
		c = new CoffeWithMilkDec(c);
		d = new DrinkWithSodaDec(d);
		b.addProduct(c);
		b.addProduct(d);
		b.confirmOrder();
		Coffe cr = new CoffeRobusta();
		b.addProduct(cr);
		String desc = "Ordine 1:\n"+c.toString()+'\n'+d.toString()+'\n'+"------------------\n"+"Ordine 2:\n"+
				cr.toString()+'\n'+"------------------\n"+"TOTALE: "+String.format("%.2f", b.getTotalPrice());
		assertEquals(desc, b.toString());
	}
	
	@Test
	public void billPayWithCashDescTest() {
		Bill b = new Bill();
		Product ca = new CoffeArabica();
		Product cr = new CoffeRobusta();
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
		Product ca = new CoffeArabica();
		Product cr = new CoffeRobusta();
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
		Product ca = new CoffeArabica();
		Product cr = new CoffeRobusta();
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
		Coffe ca = new CoffeArabica();
		Bill b = new Bill();
		b.addProduct(ca);
		b.confirmOrder();
		assertEquals(true, b.canPay());
	}
	
	@Test
	public void billCanPayAfterAddTest() {
		Coffe ca = new CoffeArabica();
		Bill b = new Bill();
		b.addProduct(ca);
		b.confirmOrder();
		b.addProduct(new Analcolico());
		assertEquals(false, b.canPay());
	}
	
	@Test
	public void billDescAfterPrint() {
		Coffe ca = new CoffeArabica();
		Bill b = new Bill();
		b.addProduct(ca);
		b.confirmOrder();
		b.clear();
		assertEquals("", b.toString());
	}
	
}
