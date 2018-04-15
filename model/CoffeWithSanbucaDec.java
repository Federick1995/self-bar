package model;

public class CoffeWithSanbucaDec extends DecoratorCoffe{

	public CoffeWithSanbucaDec(Coffe coffe) {
		super(coffe);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getPrice() {
		return super.getPrice()+1.50;
	}
	
	@Override
	public String toString() {
		return super.toString()+String.format(" con sambuca (+%.2f)", 1.50);
	}

	

}
