package model;

import java.util.Observable;
import java.util.Stack;

public class ObservableModelBill extends Observable {
	private Bill bill;
	
	public ObservableModelBill(Bill bill) {
		this.bill = bill;
	}

	public void confirmOrder() {
		bill.confirmOrder();	
	}

	public void addProduct(Product p) {
		bill.addProduct(p);
		setChanged();
	}

	public void removeProduct(Product p) {
		bill.removeProduct(p);
		setChanged();
		
	}

	public double getTotalPrice() {
		return bill.getTotalPrice();
	}
	
	public String toString() {
		return bill.toString();
	}
	
	public Stack<Product> getProduct() {
		return bill.getProduct();
	}
	
	public void setPaymentStrategy(PaymentStrategy ps) {
		bill.setPaymentStrategy(ps);
		setChanged();
	}
	
	public void clear() {
		bill.clear();
		setChanged();
	}
	
	public boolean canPay() {
		return bill.canPay();
	}
	
	public boolean orderEmpty() {
		return bill.orderEmpty();
	}

}
