package model;

public class Martini implements Drink{

	@Override
	public double getPrice() {
		return 5.00;
	}
	
	@Override
	public String toString() {
		return "Martini: "+String.format("%.2f", getPrice());
	}

	

}
