package model;

import java.util.Stack;

public class Bill extends CompositeOrder {
    private Stack<Order> order = new Stack<Order>();
    private Order actualOrder = null;
    private PaymentStrategy ps;
    
    public void setPaymentStrategy(PaymentStrategy ps) {
    	this.ps = ps;
    }
    


    @Override
    public void addProduct(Product p) {
    	if(actualOrder==null)
    		actualOrder = new Order();
    	actualOrder.addProduct(p);
    	modifyTotalOrder(p.getPrice());
    }

    @Override
    public void removeProduct(Product p) {
		actualOrder.removeProduct(p);
		modifyTotalOrder(p.getPrice()*-1);
		if(actualOrder.orderEmpty()) {
			actualOrder = null;
		}
    }

	public void confirmOrder() {
		order.add(actualOrder);
		actualOrder = null;
	}
	
	//review the print statements
	@Override
	public String toString() {
	        StringBuilder sb = new StringBuilder("");
	        int i = 1;
	        for (Order o: order) {
	        	sb.append("Ordine "+i+":\n");
	        	sb.append(o.toString());
	        	sb.append("------------------\n");
	        	i++;
	        }
	        if (actualOrder!=null) {
	        	sb.append("Ordine "+i+":\n");
				sb.append(actualOrder.toString());
				sb.append("------------------\n");
			}
	        if (!order.isEmpty() || actualOrder!=null) {
				sb.append("TOTALE: " + String.format("%.2f", getTotalPrice()));
			}
			if(ps!=null) {
		    	sb.append('\n');
		    	sb.append(ps.pay(getTotalPrice()));
		    }
	        return sb.toString();
    }

	@Override
	public Stack<Product> getProduct() {
		return actualOrder!=null?actualOrder.getProduct():null;
	}
	
	public void clear() {
		order = new Stack<Order>();
		actualOrder = null;
		ps = null;
		clearTotalPrice();
	}
	
	public boolean canPay() {
		return actualOrder==null && order.isEmpty()==false;
	}

	@Override
	public boolean orderEmpty() {
		if(actualOrder!=null)
			return actualOrder.orderEmpty();
		return false;
	}
	
}
