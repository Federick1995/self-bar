package model;

public abstract class DecoratorCoffe implements Coffe {
    private Coffe coffe;

    public DecoratorCoffe(Coffe coffe) {
        this.coffe = coffe;
    }

    @Override
    public double getPrice() {
        return coffe.getPrice();
    }
    
    @Override
    public String toString() {
    	return coffe.toString();
    }

}
