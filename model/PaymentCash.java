package model;

public class PaymentCash implements PaymentStrategy {


	@Override
	public String pay(double amount) {
		return String.format("Pagamento di: %.2f euro in contanti", amount);
	}

}
