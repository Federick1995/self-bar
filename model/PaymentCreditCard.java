package model;

public class PaymentCreditCard implements PaymentStrategy {

	@Override
	public String pay(double amount) {
		return String.format("Pagamento di: %.2f euro con carta di credito", amount*1.2);
	}

}
