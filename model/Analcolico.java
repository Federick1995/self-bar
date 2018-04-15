package model;

public class Analcolico implements Drink{

	@Override
	public double getPrice() {
		return 3.5;
	}
	
	@Override
	public String toString() {
		return "Analcolico: "+String.format("%.2f", getPrice());
	}

	

}
