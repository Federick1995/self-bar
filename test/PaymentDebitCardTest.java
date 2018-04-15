package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.PaymentDebitCard;
import model.PaymentStrategy;

public class PaymentDebitCardTest {

	@Test
	public void paymentDebitCardTest() {
		double priceBill = 5.00;
		double tax = 1.1;
		double total = priceBill*tax;
		PaymentStrategy ps = new PaymentDebitCard();
		String desc = String.format("Pagamento di: %.2f euro con carta di debito", total);
		assertEquals(desc, ps.pay(5));
	}

}
