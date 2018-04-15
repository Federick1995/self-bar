package model;

public class CoffeWithCreamDec extends DecoratorCoffe{

	public CoffeWithCreamDec(Coffe coffe) {
		super(coffe);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getPrice() {
		return super.getPrice()+0.50;
	}
	
	@Override
	public String toString() {
		return super.toString()+String.format(" con panna (+%.2f)", 0.50);
	}

	

}
