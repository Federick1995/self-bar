package model;

public abstract class DecoratorDrink implements Drink {
    private Drink drink;

    public DecoratorDrink(Drink d) {
        this.drink = d;
    }

    @Override
    public double getPrice() {
        return drink.getPrice();
    }
    
    @Override
    public String toString() {
    	return drink.toString();
    }
}
