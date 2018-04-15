package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.PaymentCreditCard;
import model.PaymentStrategy;

public class PaymentCreditCardTest {

	@Test
	public void paymentCreditCardTest() {
		double priceBill = 5.00;
		double tax = 1.2;
		double total = priceBill*tax;
		PaymentStrategy ps = new PaymentCreditCard();
		String desc = String.format("Pagamento di: %.2f euro con carta di credito", total);
		assertEquals(desc, ps.pay(5));
	}

}
