package controller;

import java.util.Stack;

import model.ObservableModelBill;
import model.PaymentStrategy;
import model.Product;
import model.SendOrderException;

public class BillController {
	private ObservableModelBill bill;
	
	public BillController(ObservableModelBill bill) {
		this.bill = bill;
	}
	
	public void addProduct(Product p) {
		bill.addProduct(p);
		bill.notifyObservers();
	}
	
	public void removeProduct(Product p)  {
		bill.removeProduct(p);
		bill.notifyObservers();
	}
	
	//make order empty method in composite order
	public void sendOrder() throws SendOrderException {
		if(bill.getProduct()!=null && !bill.getProduct().isEmpty()) {
			bill.confirmOrder();
		} else {
			throw new SendOrderException("Ordine vuoto");
		}
	}
	
	public Stack<Product> getProduct() {
		return bill.getProduct();
	}
	
	public void setPaymentStrategy(PaymentStrategy ps) {
		bill.setPaymentStrategy(ps);
		bill.notifyObservers();
	}
	
	public void clear() {
		bill.clear();
		bill.notifyObservers();
	}
	
	public boolean canPay() {
		return bill.canPay();
	}
	
	public boolean orderEmpty() {
		return bill.orderEmpty();
	}

}
