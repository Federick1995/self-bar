package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.PaymentCash;
import model.PaymentStrategy;

public class PaymentCashTest {
	//Parameterized

	@Test
	public void paymentCashTest() {
		PaymentStrategy ps = new PaymentCash();
		String desc = String.format("Pagamento di: %.2f euro in contanti", (double) 5);
		assertEquals(desc, ps.pay(5));
	}

}
