package model;

public class CoffeRobusta implements Coffe {

    @Override
    public double getPrice() {
        return 1.10;
    }

    @Override
    public String toString() {
        return "Coffe robusta: "+String.format("%.2f", getPrice());
    }

	
}
