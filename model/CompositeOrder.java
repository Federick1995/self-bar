package model;

import java.util.Stack;

public abstract class CompositeOrder {
	/**
	 * Used to confirm the actual order in Bill class.
	 * After this method, actual order is null.
	 */
	private double totalOrder = 0.0;
    public abstract void addProduct(Product p);
    public abstract void removeProduct(Product p);
    public abstract Stack<Product> getProduct();
    public abstract boolean orderEmpty(); 
    
    public void clearTotalPrice() {
    	this.totalOrder = 0.0;
    }
    
    public double getTotalPrice() {
        return totalOrder;
    }
    
    public void modifyTotalOrder(double price) {
    	totalOrder += price;
    }

}
