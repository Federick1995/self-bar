package model;

public class CoffeWithMilkDec extends DecoratorCoffe{

	public CoffeWithMilkDec(Coffe coffe) {
		super(coffe);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getPrice() {
		return super.getPrice()+0.30;
	}
	
	@Override
	public String toString() {
		return super.toString()+String.format(" con latte (+%.2f)", 0.30);
	}

	

}
