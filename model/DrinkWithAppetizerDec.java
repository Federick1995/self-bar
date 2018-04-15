package model;

public class DrinkWithAppetizerDec extends DecoratorDrink{

	public DrinkWithAppetizerDec(Drink d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getPrice() {
		return super.getPrice()+0.50;
	}
	
	@Override
	public String toString() {
		return super.toString()+String.format(" con aperitivo (+%.2f)", 0.50);
	}

	

}
