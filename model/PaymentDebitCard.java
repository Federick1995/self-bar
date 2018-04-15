package model;

public class PaymentDebitCard implements PaymentStrategy {

	
	@Override
	public String pay(double amount) {
		return String.format("Pagamento di: %.2f euro con carta di debito", amount*1.1);
	}

}
