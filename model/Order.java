package model;

import java.util.Stack;

public class Order extends CompositeOrder {
    private Stack<Product> product;

    public Order() {
        product = new Stack<Product>();
    }

    @Override
    public void addProduct(Product p) {
    	product.add(p);
    	modifyTotalOrder(p.getPrice());
    }

    @Override
    public void removeProduct(Product p) {
		modifyTotalOrder(p.getPrice() * -1);
		product.remove(p);
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder("");
        for(Product p: product) {
        	description.append(p.toString());
        	description.append("\n");
        }
        return description.toString();
    }

	@Override
	public void confirmOrder() {
		// TODO Auto-generated method stub
	}

	@Override
	public Stack<Product> getProduct() {
		return product;
	}
}
