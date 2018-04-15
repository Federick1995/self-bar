package model;

public class CoffeArabica implements Coffe {

    @Override
    public double getPrice() {
        return 1.00;
    }

    @Override
    public String toString() {
        return "Coffe arabica: "+String.format("%.2f", getPrice());
    }

	
}
