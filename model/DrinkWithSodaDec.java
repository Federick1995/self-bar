package model;

public class DrinkWithSodaDec extends DecoratorDrink{

	public DrinkWithSodaDec(Drink d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public double getPrice() {
		return super.getPrice()+1.50;
	}
	
	@Override
	public String toString() {
		return super.toString()+String.format(" con soda (+%.2f)", 1.50);
	}

	

}
